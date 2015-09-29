package org.revo.Repository;

import org.revo.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by revo on 29/09/15.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
