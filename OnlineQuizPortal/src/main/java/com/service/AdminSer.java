package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Question;
import com.bean.Quiz;
import com.bean.Result;
import com.bean.Statistics;
import com.bean.User;
import com.repository.AdminRepo;
import com.repository.Questionrepo;
import com.repository.Quizrepo;
import com.repository.Userrepo;

@Service
public class AdminSer {
	@Autowired
	Questionrepo qr;
	@Autowired
	Quizrepo qur;
	@Autowired
	Userrepo ur;
	@Autowired
	Statistics stat;
	@Autowired
	AdminRepo adr;
	
	
	
	public String adminLogin(Admin u) {
        Optional<Admin> adminOptional = adr.findByUsername("admin");
        if (adminOptional.isPresent()) {
            Admin ad = adminOptional.get();
            if (u.getUsername().equals(ad.getUsername()) && u.getPassword().equals(ad.getPassword())) {
                return "Welcome admin";
            } else {
                return "Invalid Credentials";
            }
        } else {
            return "Admin not found";
        }
    }

    public String adminupdate(Admin a) {
        Optional<Admin> adminOptional = adr.findById(1);
        if (adminOptional.isPresent()) {
            Admin ad = adminOptional.get();
            ad.setUsername(a.getUsername());
            ad.setPassword(a.getUsername());
            adr.saveAndFlush(ad);
            return "Updated";
        } else {
            return "Admin not found";
        }
    }

	
	public String addQuestion(Question q)
	{
		if(q!=null)
		{
			qr.save(q);
			return "question added";
		}
		else
		{
			return "failed to add";
		}
		
	}
	
	public String addQuiz(Quiz q)
	{
		
		if(q!=null)
		{
			qur.save(q);
			return "quiz added";
		}
		else
		{
			return "failed to add";
		}
	}
	
	public List<Quiz> viewAllQuiz()
	{
		return qur.findAll();
	}
	
	public Statistics quizInfo()
	{
		stat.setUsers(ur.findAll().size());
		stat.setQuestions(qr.findAll().size());
		stat.setQuiz(qur.listOfQuiz());
		
		return stat;

	}
	
	

}
