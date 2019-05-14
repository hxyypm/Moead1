package com.jcohy.moead.respository;

import com.jcohy.moead.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByNum(Integer num);

    User findUserByName(String name);

    User findUserByNumAndRole(Integer num,Integer role);
    @Modifying
    @Query("update User a set a.password = ?1 where a.id = ?2")
    void updatePassword(String password,Integer userId);

}
