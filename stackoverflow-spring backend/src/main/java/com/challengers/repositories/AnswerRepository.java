package com.challengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengers.entities.Answers;
import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long>{

	List<Answers> findAllByQuestionId(Long questionId);

}
