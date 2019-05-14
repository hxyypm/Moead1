package com.jcohy.moead.controller;

import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/question")
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public JsonResult list(){
        List<Question> questionList = questionService.getQuestionList();
        return JsonResult.ok().set("data",questionList);
    }

    @PostMapping("/add")
    public JsonResult add(Integer userId,String content){
        Question question = questionService.addQuestion(userId,content);
        return JsonResult.ok().set("data",question);
    }
}
