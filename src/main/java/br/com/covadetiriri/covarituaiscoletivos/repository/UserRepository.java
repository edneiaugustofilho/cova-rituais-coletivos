package br.com.covadetiriri.covarituaiscoletivos.repository;

import br.com.covadetiriri.covarituaiscoletivos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    User findByIdAndVerificationCode(UUID id, String verificationCode);
}
