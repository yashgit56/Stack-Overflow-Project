package com.challengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengers.entities.AnswerVote;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote,Long> {

}
