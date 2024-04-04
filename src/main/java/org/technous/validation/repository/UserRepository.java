package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
