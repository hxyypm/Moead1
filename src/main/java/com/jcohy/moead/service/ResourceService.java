package com.jcohy.moead.service;

import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 15:11 2019/5/6
 * Email: jia_chao23@126.com
 * ClassName: ResourceService
 * Description:
 **/
public interface ResourceService {
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Resource> findAll(Pageable pageable);


    /**
     *  查询
     * @return
     */
    List<Resource> findAll();


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Resource findById(Integer id);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Resource findByName(String name);

    /**
     * 新增或者更新
     * @param resource
     */
    Resource saveOrUpdate(Resource resource) throws ServiceException;


    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    List<Resource> findByNameLike(String key);
}
