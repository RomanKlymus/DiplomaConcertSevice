package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.account.AuthRequest;
import com.rklymus.diplomaconcertservice.dto.account.RegistrationRequest;
import com.rklymus.diplomaconcertservice.entity.Account;
import com.rklymus.diplomaconcertservice.exception.LoginFailException;
import com.rklymus.diplomaconcertservice.exception.NotFoundException;
import com.rklymus.diplomaconcertservice.exception.RegistrationFailException;
import com.rklymus.diplomaconcertservice.repository.AccountRepository;
import com.rklymus.diplomaconcertservice.repository.RoleRepository;
import com.rklymus.diplomaconcertservice.security.JwtProvider;
import com.rklymus.diplomaconcertservice.service.AccountService;
import com.rklymus.diplomaconcertservice.util.RepoUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private static final String LOGIN_FAIL_MESSAGE = "Wrong email or password";
    private AccountRepository accountRepository;
    private RepoUtil repoUtil;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    @Override
    public Account getAccountById(Long id) {
        return repoUtil.findById(Account.class, id);
    }

    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Account with email = " + email + " not found"));
    }

    @Override
    public void createAccount(RegistrationRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new RegistrationFailException("Passwords must match");
        }
        if (accountRepository.existsAccountByEmail(request.getEmail())) {
            throw new RegistrationFailException("User with this email already exists");
        }
        Account account = modelMapper.map(request, Account.class);
        account.setRole(roleRepository.getById(1L));
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public String login(AuthRequest request) {
        Account account;
        try {
            account = getAccountByEmail(request.getEmail());
        } catch (NotFoundException e) {
            throw new LoginFailException(LOGIN_FAIL_MESSAGE);
        }
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new LoginFailException(LOGIN_FAIL_MESSAGE);
        }
        return "Bearer " + jwtProvider.generateToken(account.getEmail(), account.getRole().getName());
    }

    @Override
    public Account getCurrentAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        return getAccountByEmail(email);
    }

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setRepoUtil(RepoUtil repoUtil) {
        this.repoUtil = repoUtil;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}
