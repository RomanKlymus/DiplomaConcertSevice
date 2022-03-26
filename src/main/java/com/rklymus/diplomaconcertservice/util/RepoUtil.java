package com.rklymus.diplomaconcertservice.util;

import com.rklymus.diplomaconcertservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class RepoUtil {
    private final static String ENTITY_NOT_FOUND = "%s with id = %s not found";
    private final static String REPO_NOT_FOUND = "Repository for %s entity not found";
    private final Repositories repositories;

    @Autowired
    public RepoUtil(WebApplicationContext webApplicationContext) {
        repositories = new Repositories(webApplicationContext);
    }

    public <T, ID> T findById(Class<T> entityClass, ID id) {
        Object obj = repositories.getRepositoryFor(entityClass)
                .orElseThrow(() -> new IllegalArgumentException(String.format(REPO_NOT_FOUND, entityClass)));
        JpaRepository<T, ID> repo = (JpaRepository<T, ID>) obj;

        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ENTITY_NOT_FOUND, entityClass, id)));
    }
}
