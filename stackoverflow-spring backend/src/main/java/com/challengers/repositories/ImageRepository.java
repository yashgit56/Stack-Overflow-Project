package com.challengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengers.entities.Answers;
import com.challengers.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	Image findByAnswer(Answers answer);

}
