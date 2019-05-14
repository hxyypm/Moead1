package com.jcohy.moead.controller;

import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;
import com.jcohy.moead.respository.ReplyRepository;
import com.jcohy.moead.service.QuestionService;
import com.jcohy.moead.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:20 2019/5/7
 * Email: jia_chao23@126.com
 * ClassName: QuestionController
 * Description:
 **/
@RestController
@RequestMapping("/reply")
public class ReplyController extends BaseController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/list")
    public JsonResult list(){
//        List<Question> questionList = replyService.getQuestionList();
//        return JsonResult.ok().set("data",questionList);
        return null;
    }

    @PostMapping("/add")
    public JsonResult add(Integer questionId,Integer userId,String content){
        Reply reply = replyService.addReply(questionId,userId,content);
        return JsonResult.ok().set("data",reply);
    }
}
