package com.jcohy.moead.controller;

import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.common.PageJson;
import com.jcohy.moead.model.User;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */

@Controller
@RequestMapping("/user")
public class AdminController extends BaseController{

    @Autowired
    private UserService userService;
    /**
     *
     * @param
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageJson<User> getUserList( ){
        PageRequest pageRequest = getPageRequest();
        Page<User> users = userService.findAll(pageRequest);
        PageJson<User> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(users.getSize());
        page.setData(users.getContent());
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map) {
        if (id != null) {
            User user = userService.findById(id);
            map.put("users", user);
        }
        return "admin/user/form";
    }


    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(User user ){
        User saveOrUpdate = userService.saveOrUpdate(user);
        return JsonResult.ok().set("data",saveOrUpdate);
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public JsonResult deleteUser(@PathVariable("id") Integer id){
        try {
            userService.delete(id);
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }

}
