package com.challengers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengers.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findFirstByEmail(String email) ;
}
