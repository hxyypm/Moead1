package com.jcohy.moead.service;

import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:22 2019/5/7
 * Email: jia_chao23@126.com
 * ClassName: QuestionService
 * Description:
 **/
public interface ReplyService {

    List<Reply> getReplyList();

    Reply addReply(Integer questionId,Integer userId, String content);

    void deleteReply(Integer replyId);
}
