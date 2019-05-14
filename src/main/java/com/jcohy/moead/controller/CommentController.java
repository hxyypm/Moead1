package com.jcohy.moead.controller;

import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.common.PageJson;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.User;
import com.jcohy.moead.service.QuestionService;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    /**
     *
     * @param
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageJson<Question> getUserList( ){
        PageRequest pageRequest = getPageRequest();
        Page<Question> questions = questionService.findAll(pageRequest);
        for(Question question:questions.getContent()){
            question.setUsername(userService.findById(question.getUserId()).getName());
        }
        PageJson<Question> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(questions.getSize());
        page.setData(questions.getContent());
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map) {
        if (id != null) {
            Question question = questionService.findById(id);
            map.put("question", question);
        }
        return "comment/form";
    }


//    @PostMapping("/add")
//    @ResponseBody
//    public JsonResult add(User user ){
//        User saveOrUpdate = questionService.saveOrUpdate(user);
//        return JsonResult.ok().set("data",saveOrUpdate);
//    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public JsonResult deleteUser(@PathVariable("id") Integer id){
        try {
            questionService.deleteQuestion(id);
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }

}
