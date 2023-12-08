package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Result;
import com.bean.Test;
import com.bean.User;
import com.repository.Quizrepo;
//import com.repository.Resultrepo;
import com.repository.Testrepo;
import com.repository.Userrepo;

@Service
public class UserSer {
	
	
	List<Result> finalList=new ArrayList<>();
	@Autowired
	Userrepo ur;
	@Autowired
	Quizrepo qr;
	@Autowired
	Testrepo tr;
	@Autowired
//	Resultrepo resrepo;
//	@Autowired
	User u;
	@Autowired
	Test t;

	Result r= new Result();
	
	public String userLogin(String email,String password)
	{
		u=ur.findByEmailid(email);
		if(u!=null)
		{
			
		
		if(u.getEmailid().equals(email)&&u.getPassword().equals(password))
		{
			return "login sucessfull";
		}
		else
		{
			return "invalid credentials";
		}
		
		
	}
	else
	{
		return "User not found";
	}
		

  }
	
	
	public String userRegister(User u)
	{
		
		if(ur.findByEmailid(u.getEmailid())==null)
		{
			ur.save(u);
			return "registered";
		}
		else
		{
			return "User already exists";
		}
	}
	
	public List<Object> viewAllQuiz()
	{
		return qr.listOfQuiz();
	}
	
	public String takeTest(Test t)
	{
		if(t!=null)
		{
			tr.save(t);
			return "submitted";
		}
		
		else
		{
			return "submission failed";
		}
			
	}
	
	public List<Test> getTestList()
	{
		return tr.findAll();
	}
	
	public List<Result> result() {
	    List<Test> obj = tr.findAll();
	    List<User> users = ur.findAll();
	    List<Result> finalList = new ArrayList<>();

	    for (User user : users) {
	        int mark = 2;
	        String email = user.getEmailid();
	        System.out.println(email);

	        for (Test ob : obj) {
	            if (user.getUid() == ob.getUserid().getUid()) {
	                if (ob.getTestans() == ob.getQuestionid().getAns()) {
	                    mark++;
	                }
	                System.out.println("inside: " + mark);
	            }
	        }
	        System.out.println("outside: " + mark);

	        // Check if the user's email already exists in finalList
	        boolean userExists = false;
	        for (Result result : finalList) {
	            if (result.getEmail() != null && result.getEmail().equals(email)) {
	                result.setMarks(mark);
	                userExists = true;
	                break;
	            }
	        }

	        // If the user's email does not exist, add a new entry to finalList
	        if (!userExists) {
	            Result userResult = new Result(email, mark); // Create a new Result object
	            finalList.add(userResult);
	        }
	    }

	    System.out.println("final: " + finalList);

	    Collections.sort(finalList);
	    return finalList;
	}
}