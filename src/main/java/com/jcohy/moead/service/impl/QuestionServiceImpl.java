package com.jcohy.moead.service.impl;

import com.jcohy.date.DateUtils;
import com.jcohy.moead.enums.RoleType;
import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;
import com.jcohy.moead.model.User;
import com.jcohy.moead.respository.QuestionRepository;
import com.jcohy.moead.respository.ReplyRepository;
import com.jcohy.moead.service.QuestionService;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:23 2019/5/7
 * Email: jia_chao23@126.com
 * ClassName: QuestionServiceImpl
 * Description:
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public List<Question> getQuestionList() {
        List<Question> questions = questionRepository.findAll();
        for(Question question:questions){
            User user = userService.findById(question.getUserId());
            question.setUsername(user.getName());
            question.setRoleName(RoleType.getNameByValue(user.getRole()));
            List<Reply> replies = replyRepository.findByQuestionId(question.getId());
            if(replies != null && replies.size() > 0){
                for(Reply reply:replies){
                    User user1 = userService.findById(reply.getUserId());
                    reply.setUsername(user1.getName());
                    reply.setRoleName(RoleType.getNameByValue(user1.getRole()));
                }
            }
            question.setReplies(replies);
        }
        return questions;
    }

    @Override
    public Question addQuestion(Integer userId,String content) {
        Question question = new Question();
        question.setType(1);
        question.setDescription(content);
        question.setUserId(userId);
        question.setTime(DateUtils.dateToStr(new Date(),"yyyy-mm-dd HH:mm:ss"));
        Question question1 = questionRepository.saveAndFlush(question);
        return question1;
    }

    @Override
    @Transactional
    public void deleteQuestion(Integer questionId) {
        replyRepository.deleteAllByQuestionId(questionId);
         questionRepository.deleteById(questionId);
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id).get();
    }

//    @Override
//    public User saveOrUpdate(Question question) throws ServiceException {
//        Integer id = question.getId();
//        Question dbUser = null;
//        if(id != null){
//            dbUser = questionRepository.findById(id).get();
//            if(user.getEmail()!=null) dbUser.setEmail(user.getEmail());
//            if(user.getName()!=null) dbUser.setName(user.getName());
//            if(user.getBirth()!=null) dbUser.setBirth(user.getBirth());
//            if(user.getPhone()!=null) dbUser.setPhone(user.getPhone());
//            if(user.getSex()!=null) dbUser.setSex(user.getSex());
//            userRepository.saveAndFlush(dbUser);
//        }else{
//            user.setPassword("123456");
//            dbUser = userRepository.saveAndFlush(user);
//
//        }
//        return dbUser;
//    }
}
