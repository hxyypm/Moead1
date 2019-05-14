package com.jcohy.moead.respository;

import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    List<Reply> findByQuestionId(Integer id);

    void deleteAllByQuestionId(Integer questionId);

}
