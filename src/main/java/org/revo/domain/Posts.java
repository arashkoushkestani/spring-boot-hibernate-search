package org.revo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
public class Posts {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @ManyToOne
    @JoinColumn
    @Getter
    @Setter
    private User user;
    @Field
    @Getter
    @Setter
    private String content;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "posts")
    @Getter
    @Setter
    private Set<Tags> tags = new HashSet<>();
    @Temporal(TemporalType.TIMESTAMP)
    @DateBridge(resolution = Resolution.DAY)
    @Getter
    @Setter
    private Date createdDate;

    @Transient
    public Posts addToTags(Tags tags) {
       this.tags.add(tags);
        return this;
    }

    @Transient
    public Posts addToTags(Set<Tags> tags) {
          this.tags.addAll(tags);
        return this;
    }
}
