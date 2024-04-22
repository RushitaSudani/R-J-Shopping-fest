package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByUsername(String username);
    Optional<User> findOneByEmailAndPassword(String email, String password);

    @Query(value = "SELECT user.id,user.username,user.email,role.name FROM validation.user,validation.role,validation.user_roles where user.username=?1 and user.password=?2 " +
            "and user.id = user_roles.user_id and user_roles.role_id = role.id limit 1",
            nativeQuery = true)
    public List<Object[]> getLoginDetail(String username, String password);
}
