package devc.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devc.work.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

