package org.example.chat.repository;

import org.example.chat.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByName(String name);

    @Query(value = "select * from posts p join post_user ps on ps.post_id=p.id where user_id= :id",
            nativeQuery = true)
    List<Post> findAllPostByUserId(@Param("id") long id);
}
