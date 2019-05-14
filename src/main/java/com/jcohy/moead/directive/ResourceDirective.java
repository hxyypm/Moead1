package com.jcohy.moead.directive;

import com.jcohy.moead.model.Resource;
import com.jcohy.moead.respository.AlgorithmicRepository;
import com.jcohy.moead.service.ResourceService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by jiac on 2018/5/3.
 * ClassName  : com.jcohy.moead.directive
 * Description  :
 */
@Component
public class ResourceDirective implements TemplateDirectiveModel{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResourceDirective.class);

    @Autowired
    private ResourceService resourceService;


    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        List<Resource> list = resourceService.findAll();
        logger.warn("resource:{}",list.size());
        environment.setVariable("list", new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build().wrap(list));
        if (templateDirectiveBody != null) {
            templateDirectiveBody.render(environment.getOut());
        }
    }
}
