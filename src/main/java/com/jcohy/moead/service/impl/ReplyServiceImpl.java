package com.jcohy.moead.service.impl;

import com.jcohy.date.DateUtils;
import com.jcohy.moead.enums.RoleType;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;
import com.jcohy.moead.model.User;
import com.jcohy.moead.respository.QuestionRepository;
import com.jcohy.moead.respository.ReplyRepository;
import com.jcohy.moead.service.ReplyService;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<Reply> getReplyList() {
        return null;
    }

    @Override
    public Reply addReply(Integer questionId,Integer userId, String content) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setUserId(userId);
        reply.setQuestionId(questionId);

        reply.setTime(DateUtils.dateToStr(new Date(),"yyyy-mm-dd HH:mm:ss"));
        Reply dbReply = replyRepository.saveAndFlush(reply);
        User user = userService.findById(dbReply.getUserId());
        dbReply.setUsername(user.getName());
        dbReply.setRoleName(RoleType.getNameByValue(user.getRole()));
        return replyRepository.saveAndFlush(reply);
    }

    @Override
    public void deleteReply(Integer replyId) {

    }
}
