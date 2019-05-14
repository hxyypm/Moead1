package com.jcohy.moead.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.enums.RoleType;
import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.User;
import com.jcohy.moead.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName  : LoginController
 * Description  :登录模块处理
 */
@Controller
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    /**
     * 登录处理
     *
     * @param num
     * @param password
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public JsonResult login(Integer num, String password, @RequestParam(required = false) String role,
                            HttpServletRequest request) {
        try {
            if (num == null || StringUtils.isEmpty(password)) {
                return JsonResult.fail("用户名或者密码不能为空");
            }
            HttpSession session = request.getSession();
            logger.info("name:{}  password:{}  role:{}", num, password, role);
            User login = userService.login(num, password, RoleType.getValueByName(role));
            session.setAttribute("user", login);
            if (login == null) {
                return JsonResult.fail("登录失败,用户名不存在");
            }
            if (!login.getPassword().equals(password)) {
                return JsonResult.fail("登录失败,用户名账号密码不匹配");
            }

//            if (StringUtils.trim(role).equals("teacher")) {
//                return JsonResult.ok().set("returnUrl", "/resource/main");
//            }

//            if (StringUtils.trim(role).equals("admin")) {
//                return JsonResult.ok().set("returnUrl", "/admin/main");
//            }

            return JsonResult.ok("登录成功").set("data", login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.fail("角色不存在");
    }


    /**
     * 注册接口
     *
     * @param num      账号 必填
     * @param phone    电话 必填
     * @param password 密码 必填
     * @param name     姓名 必填
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public JsonResult register(Integer num, String phone, String password, String name) {
        if (num == null || phone == null || StringUtils.hashEmpty(name, password)) {
            return JsonResult.fail("参数不能为空");
        }
        boolean exist = userService.checkUser(num);
        if (exist) {
            return JsonResult.fail("用户已存在");
        }
        User user = new User();
        user.setNum(num);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setRole(RoleType.STUDENT.getValue());
        userService.saveOrUpdate(user);
        return JsonResult.ok("注册成功").set("data", user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @GetMapping("/update")
    @ResponseBody
    public JsonResult update(User user) {
        try {
            User stu = userService.saveOrUpdate(user);
            return JsonResult.ok().set("data", stu);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }

    /**
     * 修改密码
     *
     * @param userId
     * @return
     */
    @GetMapping("/changePass")
    @ResponseBody
    public JsonResult changePass(Integer userId, String oldPassword, String newPassword, String rePassword) {
        try {

//            logger.error("role:{}", user.getRole());
            if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(rePassword)) {
                return JsonResult.fail("参数不完整");
            }
            if (!newPassword.equals(rePassword)) {
                return JsonResult.fail("两次输入密码不一致");
            }
            User dbUser = userService.findById(userId);
            if (dbUser.getPassword() != null && dbUser.getPassword().equals(oldPassword)) {
                userService.updatePassword(newPassword,userId);
                return JsonResult.ok("修改成功");
            } else {
                return JsonResult.fail("密码校验失败");
            }
        } catch (ServiceException e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    /**
     * 注销用户
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/user/login";
    }

    @GetMapping("/admin/update")
    public String updatePassword() {
        return "update";
    }
}
