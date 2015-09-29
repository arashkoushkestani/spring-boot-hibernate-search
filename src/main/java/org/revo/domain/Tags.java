package org.revo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by revo on 29/09/15.
 */
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Indexed
public class Tags {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Field
    @Getter
    @Setter
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Set<Posts> posts = new HashSet<>();

    @Transient
    public Tags addToPosts(Posts posts) {
        this.posts.add(posts);
        return this;
    }

    @Transient
    public Tags addToPosts(Set<Posts> posts) {
        this.posts.addAll(posts);
        return this;
    }
}
