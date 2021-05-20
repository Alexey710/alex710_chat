package org.example.chat.repository;

import org.example.chat.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select * from users u where u.id != :id",
            nativeQuery = true)
    List<User> findAllUsersExceptCurrent(@Param("id") long id);

}
