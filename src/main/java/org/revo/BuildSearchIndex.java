package org.revo;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

/**
 * Created by revo on 26/09/15.
 */
@Configuration
@Component
public class BuildSearchIndex implements ApplicationListener<ContextRefreshedEvent> {
    @PersistenceContext
    private EntityManager em;

    @Bean
    public FullTextEntityManager fullTextEntityManager() {
        return getFullTextEntityManager(em);
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        try {
            FullTextEntityManager fullTextEntityManager =
                    getFullTextEntityManager(em);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
        }
    }
}
