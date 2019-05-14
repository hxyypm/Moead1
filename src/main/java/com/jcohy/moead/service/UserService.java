package com.jcohy.moead.service;

import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param num  管理员编号
     * @param password
     * @return
     * @throws Exception
     */
    User login(Integer num, String password,Integer role) throws Exception;


    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<User> findAll(Pageable pageable);


    /**
     *  查询
     * @return
     */
    List<User> findAll();


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据ID查询
     * @param num
     * @return
     */
    User findByNum(Integer num);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 新增或者更新用户
     * @param user
     */
    User saveOrUpdate(User user) throws ServiceException;

    /**
     * 检查用户是否存在
     * @param num
     * @return
     */
    boolean checkUser(Integer num);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改用户密码
     * @param password
     */
    void updatePassword(String password,Integer userId);
}

