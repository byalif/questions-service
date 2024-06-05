package com.byalif.questionsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.byalif.questionsservice.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query(value = "SELECT * from question WHERE question.question_category = :category", nativeQuery = true)
	List<Question> findByCategory(String category);
	
	
	@Query(value = "SELECT * FROM question WHERE question.question_category = :category ORDER BY RAND()\n"
			+ "LIMIT :len", nativeQuery = true)
	List<Question> findRandByCategory(String category, int len);

}
