package com.jcohy.moead.service;

import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:22 2019/5/7
 * Email: jia_chao23@126.com
 * ClassName: QuestionService
 * Description:
 **/
public interface QuestionService {


    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Question> findAll(Pageable pageable);

    List<Question> getQuestionList();

    Question addQuestion(Integer userId,String content);

    void deleteQuestion(Integer questionId);


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Question findById(Integer id);
//
//    /**
//     * 新增或者更新用户
//     * @param question
//     */
//    User saveOrUpdate(Question question) throws ServiceException;

}
