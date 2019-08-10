package uz.google.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.google.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
