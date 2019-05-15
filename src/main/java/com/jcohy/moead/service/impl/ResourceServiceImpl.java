package com.jcohy.moead.service.impl;

import com.jcohy.date.DateUtils;
import com.jcohy.moead.exception.ServiceException;
import com.jcohy.moead.model.Resource;
import com.jcohy.moead.respository.ResourceRepository;
import com.jcohy.moead.service.ResourceService;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 15:13 2019/5/6
 * Email: jia_chao23@126.com
 * ClassName: ResourceServiceImpl
 * Description:
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Resource> findAll(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = resourceRepository.findAll();
        for (Resource resource :resources) {
            resource.setUser(userService.findById(resource.getUserId()));
        }
        return resourceRepository.findAll();
    }

    @Override
    public Resource findById(Integer id) {
        return resourceRepository.findById(id).get();
    }

    @Override
    public Resource findByName(String name) {
        return resourceRepository.findResourceByName(name);
    }

    @Override
    public Resource saveOrUpdate(Resource resource) throws ServiceException {
        Resource dbresource = null;
        if (resource.getId() != null) {
            dbresource = resourceRepository.findById(resource.getId()).get();
            dbresource.setDescription(resource.getDescription());
        } else {
            resource.setTime(DateUtils.dateToStr(new Date(),"yyyy-mm-dd HH:mm:ss"));
            dbresource = resource;
        }
        return resourceRepository.saveAndFlush(dbresource);
    }

    @Override
    public void delete(Integer id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public List<Resource> findByNameLike(String key) {
        List<Resource> resources = resourceRepository.findByName(key);
        for(Resource resource:resources){
            resource.setUser(userService.findById(resource.getUserId()));
        }
        return resourceRepository.findByName(key);
    }
}
