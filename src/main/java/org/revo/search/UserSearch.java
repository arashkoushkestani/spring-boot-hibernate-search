package org.revo.search;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by revo on 26/09/15.
 */
@Repository
@Transactional
public class UserSearch {
    @Autowired
    FullTextEntityManager fTtEM;

    private <T> QueryBuilder QueryBuild(Class<T> TClass) {
        return fTtEM.getSearchFactory().buildQueryBuilder().forEntity(TClass).get();
    }

    private Query GetQuery(String c, QueryBuilder queryBuilder, String... fields) {
        return queryBuilder.keyword().onFields(fields).matching(c).createQuery();
    }

    public <T> List<T> search(String c, Class<T> TClass, String... fields) {
        return fTtEM.createFullTextQuery(GetQuery(c, QueryBuild(TClass), fields), TClass).
                getResultList();
    }
}
