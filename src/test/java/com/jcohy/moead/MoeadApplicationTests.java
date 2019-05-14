package com.jcohy.moead;

import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;
import com.jcohy.moead.respository.QuestionRepository;
import com.jcohy.moead.respository.ReplyRepository;
import com.jcohy.moead.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoeadApplicationTests {

	@Autowired
	private QuestionService questionService;


	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Test
	public void contextLoads() {
		List<Question> questionList = questionService.getQuestionList();
////		List<Question> questionList = questionRepository.findAll();
//		List<Reply> questionList = replyRepository.findByQuestionId(1);
		for(Question question:questionList){
			System.out.println(question.toString());
		}
	}

}
