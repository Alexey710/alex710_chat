package org.example.chat.repository;

import org.example.chat.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findByAuthority(String authority);
}