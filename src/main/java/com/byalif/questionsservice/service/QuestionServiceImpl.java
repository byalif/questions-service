package com.byalif.questionsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.byalif.questionsservice.DTO.KafkaDTO;
import com.byalif.questionsservice.DTO.QuestionDTO;
import com.byalif.questionsservice.DTO.Quiz;
import com.byalif.questionsservice.DTO.ResponseDTO;
import com.byalif.questionsservice.entity.Question;
import com.byalif.questionsservice.kafka.KafkaProducer;
import com.byalif.questionsservice.repository.QuestionRepository;


@Service
public class QuestionServiceImpl implements QuestionService{
	final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	QuestionRepository questionRepository;
	
    @Autowired
    KafkaProducer kafkaProducer;


	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		return ResponseEntity.status(HttpStatus.OK).body(questionRepository.findAll());
	}


	@Override
	public ResponseEntity<Question> createQuestion(Question question) {
		return ResponseEntity.status(HttpStatus.CREATED).body(questionRepository.save(question));
	}


	@Override
	public ResponseEntity<String> deleteQuestion(int id) {
		questionRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Question deleted!");
	}


	@Override
	public ResponseEntity<List<Question>> getByCategory(String category) {
		return ResponseEntity.status(HttpStatus.OK).body(questionRepository.findByCategory(category));
	}
	
	@Override
	public ResponseEntity<List<Question>> getRandByCategory(String category, int limit) {
		return ResponseEntity.status(HttpStatus.OK).body(questionRepository.findRandByCategory(category, limit));
	}


	@Override
	public ResponseEntity<ResponseDTO> newClientSubmission(QuestionDTO questions) {
		try {
            // Publish a message to Kafka topic containing necessary information
			KafkaDTO kafkaDTO = new KafkaDTO();
			kafkaDTO.setQuestionDTO(questions);
            kafkaProducer.sendMessage(kafkaDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Email request sent to Kafka topic: "+ questions.getEmail()));
        } catch (Exception e) {
        	// catch this later
            log.error("Failed to send email request to Kafka topic", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Failed to send email request to Kafka topic"));
        }
	}


	@Override
	public ResponseEntity<Quiz> createQuestions(QuestionDTO questionDTO, String name) {
	    List<Question> questions = questionDTO.getQuestions();
	    
	    // List to hold the saved questions
	    List<Question> savedQuestions = new ArrayList<>();
	    
	    // Save each question individually
	    for (Question question : questions) {
	        // Save the question entity
	        Question savedQuestion = questionRepository.save(question);
	        
	        // Add the saved question to the list of saved questions
	        savedQuestions.add(savedQuestion);
	    }
	    
	    Quiz quiz = createQuiz(savedQuestions, name);
	    
	    
	    // You can return ResponseEntity with the list of saved questions if needed
	    return ResponseEntity.status(HttpStatus.OK).body(quiz);
	}


	@Override
	public Quiz createQuiz(List<Question> questions, String name) {
		// Set up HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Set up the request body
        HttpEntity<List<Question>> requestEntity = new HttpEntity<>(questions, headers);
        
        // Send the POST request
        ResponseEntity<Quiz> responseEntity = restTemplate.exchange(
            "http://quiz-service-svc/quiz/create/newQuiz/{name}",
            HttpMethod.POST,
            requestEntity,
            Quiz.class,
            name
        );
        
        // Return the response body
        return responseEntity.getBody();
	}

}
