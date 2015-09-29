package org.revo.domain;

import lombok.*;
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
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Field
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Posts> posts = new HashSet<>();

    @Transient
    public User addToPosts(Posts posts) {
        this.posts.add(posts);
        return this;
    }

    @Transient
    public User addToPosts(Set<Posts> posts) {
        this.posts.addAll(posts);
        return this;
    }

}
