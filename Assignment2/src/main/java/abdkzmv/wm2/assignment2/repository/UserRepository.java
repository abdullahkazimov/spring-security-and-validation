package abdkzmv.wm2.assignment2.repository;

import abdkzmv.wm2.assignment2.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "update users set roles = ? where id = ?", nativeQuery = true)
    void saveToUser(String roles, Long userId);
}
