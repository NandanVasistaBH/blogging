package com.ndn.user_service.repository;
import java.util.Optional;
import com.ndn.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository("UserRepository")
public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
