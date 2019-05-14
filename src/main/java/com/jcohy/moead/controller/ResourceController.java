package com.jcohy.moead.controller;

import com.jcohy.moead.common.JsonResult;
import com.jcohy.moead.common.PageJson;
import com.jcohy.moead.model.Resource;
import com.jcohy.moead.model.User;
import com.jcohy.moead.service.ResourceService;
import com.jcohy.moead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 10:05 2019/5/9
 * Email: jia_chao23@126.com
 * ClassName: ResourceController
 * Description:
 **/
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    @ResponseBody
    public PageJson<Resource> list(@SessionAttribute("user")User user, ModelMap map){
        PageRequest pageRequest = getPageRequest();
        Page<Resource> plans = resourceService.findAll(pageRequest);

        List<Resource> resourceList = plans.stream().filter(x -> x.getUserId() == user.getId()).collect(Collectors.toList());
        for (Resource resource: resourceList) {
            resource.setUser(userService.findById(resource.getUserId()));
        }
        PageJson<Resource> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(resourceList.size());
        page.setData(resourceList);
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map) {
//        List<Resource> colleges = resourceService.findAll();
//        List<Resource> requirements = requirementService.findAll();
//        map.put("colleges", colleges);
//        map.put("requirements", requirements);
        if (id != null) {
            Resource resource = resourceService.findById(id);
            map.put("resource", resource);
        }
        return "resource/form";
    }

    @PostMapping("/add")
    @ResponseBody
    public JsonResult addHc(Resource resource){
        try {
            Resource dbresource = resourceService.saveOrUpdate(resource);
            if(dbresource != null ){
                dbresource.setDescription(resource.getDescription());
            }else{
                dbresource = resource;
            }
            Resource req = resourceService.saveOrUpdate(dbresource);
            return JsonResult.ok().set("data", req);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public JsonResult searchJob(String key){
        List<Resource> list = resourceService.findByNameLike(key);
        return JsonResult.ok().set("data", list);
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public JsonResult deleteJob(@PathVariable("id") Integer id){
        try {
            resourceService.delete(id);
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }
}
