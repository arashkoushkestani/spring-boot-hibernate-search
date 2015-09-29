package org.revo.domain;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;
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
public class Posts {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn
    private User user;
    @Field
    private String content;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "posts")
    private Set<Tags> tags = new HashSet<>();
    @Temporal(TemporalType.TIMESTAMP)
    @DateBridge(resolution = Resolution.DAY)
    private Date createdDate;

    @Transient
    public Posts addToTags(Tags tags) {
       this.tags.add(tags);
        tags.getPosts().add(this);
        return this;
    }

    @Transient
    public Posts addToTags(Set<Tags> tags) {
          this.tags.addAll(tags);
        return this;
    }
}
