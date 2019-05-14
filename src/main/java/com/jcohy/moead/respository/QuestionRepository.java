package com.jcohy.moead.respository;

import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface QuestionRepository extends JpaRepository<Question,Integer> {



}
