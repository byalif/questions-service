package com.byalif.questionsservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.byalif.questionsservice.DTO.QuestionDTO;
import com.byalif.questionsservice.DTO.Quiz;
import com.byalif.questionsservice.DTO.ResponseDTO;
import com.byalif.questionsservice.entity.Question;


@Service
public interface QuestionService {

	ResponseEntity<List<Question>> getAllQuestions();

	ResponseEntity<Question> createQuestion(Question question);
	
	ResponseEntity<String> deleteQuestion(int id);

	ResponseEntity<List<Question>> getByCategory(String category);

	ResponseEntity<List<Question>> getRandByCategory(String category, int len);


	ResponseEntity<ResponseDTO> newClientSubmission(QuestionDTO questions);

	ResponseEntity<Quiz> createQuestions(QuestionDTO questionDTO, String name);
	
	Quiz createQuiz(List<Question> questions, String name);
	
}
