package com.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Admin;
import com.bean.Question;
import com.bean.Quiz;
import com.bean.Result;
import com.bean.Statistics;
import com.bean.Test;
import com.bean.User;
import com.service.AdminSer;
import com.service.UserSer;

@RestController
@RequestMapping(value = "/quizapp", produces = "application/json")
public class MainController {
	@Autowired
	UserSer us;
	@Autowired
	AdminSer as;
	
	@PostMapping(value = "/userLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userLogin(@RequestBody User user) {
        String loginResult = us.userLogin(user.getEmailid(), user.getPassword());
        return ResponseEntity.ok(loginResult);
    }
	
	 @PostMapping(value = "/userRegister", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> userRegister(@RequestBody User user) {
	        String registerResult = us.userRegister(user);
	        return ResponseEntity.ok(registerResult);
	    }
	 @PostMapping(value = "/adminLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
	        String loginResult = as.adminLogin(admin);
	        return ResponseEntity.ok(loginResult);
	    }

	
		@PostMapping(value="adminupdate", consumes = MediaType.APPLICATION_JSON_VALUE)
		public String adminUpdate(@RequestBody Admin u)
		{
				return as.adminupdate(u);
		}

	@PostMapping(value="addQuestions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addQuestion(@RequestBody Question q)
	{
			return as.addQuestion(q);
	}
	
	@PostMapping(value="addQuiz", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addQuiz(@RequestBody Quiz q)
	{
			return as.addQuiz(q);
	}
	
		@GetMapping(value="viewAllQuiz", produces= MediaType.APPLICATION_JSON_VALUE)
		public List<Quiz> viewAllQuiz()
		{
				return as.viewAllQuiz();
		}
		
		@GetMapping(value="quizinfo", produces= MediaType.APPLICATION_JSON_VALUE)
		public Statistics quizinfo()
		{
				return as.quizInfo();
		}
		
				@GetMapping(value="viewQuiz", produces= MediaType.APPLICATION_JSON_VALUE)
				public List<Object> viewQuiz()
				{
						return us.viewAllQuiz();
				}


				@PostMapping(value="takeTest", consumes = MediaType.APPLICATION_JSON_VALUE)
				public String takeTest(@RequestBody Test t)
				{
						return us.takeTest(t);
				}
				
				@GetMapping(value="getAllTest", produces= MediaType.APPLICATION_JSON_VALUE)
				public List<Test> getAllTest()
				{
						return us.getTestList();
				}
				
				@GetMapping(value="getresult", produces= MediaType.APPLICATION_JSON_VALUE)
				public List<Result> getresult()
				{
						return us.result();
				}
				
				@GetMapping(value="getAdminResult", produces= MediaType.APPLICATION_JSON_VALUE)
				public List<Result> getAdminResult()
				{
						return us.result();
				}
				
}
