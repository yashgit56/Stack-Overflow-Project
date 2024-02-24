package com.challengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengers.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
