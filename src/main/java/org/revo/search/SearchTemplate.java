package org.revo.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashraf on 9/28/2015.
 */
@Repository
@Transactional
public class SearchTemplate {
    @Autowired
    FullTextEntityManager fTtEM;

    private <T> QueryBuilder QueryBuild(Class<T> TClass) {
        return fTtEM.getSearchFactory().buildQueryBuilder().forEntity(TClass).get();
    }

    private Query GetQuery(String c, QueryBuilder queryBuilder, String... fields) {
        return queryBuilder.keyword().onFields(fields).matching(c).createQuery();
    }

    public <T> List<T> search(String c, Class<T> TClass, String... fields) {
        if (fields.length == 0) fields = Arrays.asList(TClass.getDeclaredFields()).stream()
                .filter(field -> field.isAnnotationPresent(Field.class))
                .map(field -> field.getName()).toArray(String[]::new);
        return fTtEM.createFullTextQuery(GetQuery(c, QueryBuild(TClass), fields), TClass).getResultList();
    }
}
