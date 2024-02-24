package com.challengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.challengers.entities.QuestionVote;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote,Long>{

}
