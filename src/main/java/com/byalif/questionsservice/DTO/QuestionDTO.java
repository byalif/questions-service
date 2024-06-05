package com.byalif.questionsservice.DTO;

import java.util.List;
import com.byalif.questionsservice.entity.Question;
public class QuestionDTO {
	
	String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
