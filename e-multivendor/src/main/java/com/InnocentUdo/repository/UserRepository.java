package com.InnocentUdo.repository;

import com.InnocentUdo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
