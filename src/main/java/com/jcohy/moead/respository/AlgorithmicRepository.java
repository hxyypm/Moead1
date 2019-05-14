package com.jcohy.moead.respository;

import com.jcohy.moead.model.Algorithmic;
import com.jcohy.moead.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface AlgorithmicRepository extends JpaRepository<Algorithmic,Integer> {

    @Modifying
    @Query("update Algorithmic a set a.content = ?2,a.userId = ?3 where a.id = ?1")
    void updateContent(Integer id,String content,Integer userId);

}
