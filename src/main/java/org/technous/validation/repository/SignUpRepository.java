package org.technous.validation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.SignUp;
import org.technous.validation.request.LoginRequest;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    SignUp findByEmail(String email);
}
