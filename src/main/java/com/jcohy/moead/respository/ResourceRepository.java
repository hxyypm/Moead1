package com.jcohy.moead.respository;

import com.jcohy.moead.model.Question;
import com.jcohy.moead.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface ResourceRepository extends JpaRepository<Resource,Integer> {


    Resource findResourceByName(String name);

    Resource findResourceByNameAndUserId(String name,Integer userId);

    @Query(value = "select * from resource a where a.name like %?1%", nativeQuery = true)
    List<Resource> findByName(String key);
}
