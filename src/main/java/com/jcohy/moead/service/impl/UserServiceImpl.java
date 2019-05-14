package com.jcohy.moead.service.impl;

import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.User;
import com.jcohy.moead.respository.UserRepository;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(Integer num, String password,Integer role) throws Exception {
        return userRepository.findUserByNumAndRole(num,role);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByNum(Integer num) {
        return userRepository.findUserByNum(num);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User saveOrUpdate(User user) throws ServiceException {
        Integer userId = user.getId();
        User dbUser = null;
        if(userId != null){
            dbUser = userRepository.findById(userId).get();
            if(user.getEmail()!=null) dbUser.setEmail(user.getEmail());
            if(user.getName()!=null) dbUser.setName(user.getName());
            if(user.getBirth()!=null) dbUser.setBirth(user.getBirth());
            if(user.getPhone()!=null) dbUser.setPhone(user.getPhone());
            if(user.getSex()!=null) dbUser.setSex(user.getSex());
            userRepository.saveAndFlush(dbUser);
        }else{
            user.setPassword("123456");
            dbUser = userRepository.saveAndFlush(user);

        }
        return dbUser;
    }

    @Override
    public boolean checkUser(Integer num) {
        User dbUser = userRepository.findUserByNum(num);
        return dbUser != null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePassword(String password,Integer userId) {
        userRepository.updatePassword(password,userId);
    }
}
