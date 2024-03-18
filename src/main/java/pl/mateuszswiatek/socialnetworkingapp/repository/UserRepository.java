package pl.mateuszswiatek.socialnetworkingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long userId);
    boolean existsByUsernameAndIdNot(String username, Long userId);
}
