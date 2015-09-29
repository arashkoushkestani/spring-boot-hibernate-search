package org.revo.Repository;

import org.revo.domain.Posts;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by revo on 29/09/15.
 */
public interface PostsRepository extends CrudRepository<Posts, Long> {
}
