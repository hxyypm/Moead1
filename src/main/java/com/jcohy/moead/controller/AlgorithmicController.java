package com.jcohy.moead.controller;

import com.jcohy.date.DateUtils;
import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.model.Algorithmic;
import com.jcohy.moead.model.User;
import com.jcohy.moead.respository.AlgorithmicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 12:30 2019/5/9
 * Email: jia_chao23@126.com
 * ClassName: AlgorithmicController
 * Description:
 **/
@Controller
@RequestMapping("/algorithmic")
public class AlgorithmicController extends BaseController {

    @Autowired
    private AlgorithmicRepository algorithmicRepository;

    @GetMapping("/index")
    public String index(ModelMap map){
        Algorithmic algorithmic = algorithmicRepository.findById(1).get();
        map.put("algorithmic",algorithmic);
        return "algorithmic/index";
    }

    @GetMapping("/info")
    @ResponseBody
    public JsonResult info(){
        Algorithmic algorithmic = algorithmicRepository.findById(1).get();
        return JsonResult.ok().set("data",algorithmic);
    }

    @PostMapping("update")
    @ResponseBody
    @Transactional
    public JsonResult update(String content, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Algorithmic algorithmic = algorithmicRepository.findById(1).get();
        algorithmic.setContent(content);
        algorithmic.setUserId(user.getId());
        algorithmic.setUpdateDate(DateUtils.dateToStr(new Date(),"yyyy-mm-dd HH:mm:ss"));
        algorithmicRepository.saveAndFlush(algorithmic);
        return JsonResult.ok().set("data",content);
    }
}
