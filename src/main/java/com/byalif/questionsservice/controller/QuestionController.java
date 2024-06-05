package com.byalif.questionsservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byalif.questionsservice.DTO.QuestionDTO;
import com.byalif.questionsservice.DTO.Quiz;
import com.byalif.questionsservice.DTO.ResponseDTO;
import com.byalif.questionsservice.entity.Question;
import com.byalif.questionsservice.service.QuestionServiceImpl;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {


	@Autowired
	QuestionServiceImpl questionService;

	@GetMapping("/Hello")
	public String hello() {
		return "Hello!!!";
	}

	@GetMapping("/getAll")
	ResponseEntity<List<Question>> getAllQuestions(@RequestHeader("username") String username) {
		return questionService.getAllQuestions();
	}
	
	@PostMapping("/client/submitQuestions")
	ResponseEntity<ResponseDTO> newClientSubmission(@RequestBody QuestionDTO questions) {
		return questionService.newClientSubmission(questions);
	}
	
	@GetMapping("/get/{category}")
	ResponseEntity<List<Question>> getById(@PathVariable String category) {
		return questionService.getByCategory(category);
	}

	@GetMapping("/getRand/{category}/{limit}")
	ResponseEntity<List<Question>> getById(@PathVariable String category, @PathVariable int limit) {
		return questionService.getRandByCategory(category, limit);
	}

	@PostMapping("/create")
	ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		return questionService.createQuestion(question);
	}
	
	@PostMapping("/create/newQuiz")
	ResponseEntity<Quiz> createQuestions(@RequestBody QuestionDTO questionDTO) {
		return questionService.createQuestions(questionDTO, LocalDate.now().toString());
	}

	@PostMapping("/delete/{id}")
	ResponseEntity<String> deleteQuestion(@PathVariable int id) {
		return questionService.deleteQuestion(id);
	}

}
