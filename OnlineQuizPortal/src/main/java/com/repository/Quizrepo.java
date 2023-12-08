package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bean.Quiz;
@Repository
public interface Quizrepo extends JpaRepository<Quiz, Integer>{

	@Query("SELECT DISTINCT q.title, COUNT(DISTINCT q.quizno) FROM Quiz q GROUP BY q.title,q.quizno")
	public List<Object> listOfQuiz();

}
