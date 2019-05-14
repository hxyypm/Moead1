package com.jcohy.moead.directive;

import com.jcohy.moead.model.Algorithmic;
import com.jcohy.moead.respository.AlgorithmicRepository;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class AlgorithmicDirective implements TemplateDirectiveModel {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResourceDirective.class);

    @Autowired
    private AlgorithmicRepository algorithmicRepository;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Algorithmic algorithmic = algorithmicRepository.findById(1).get();
        logger.warn("algorithmic:{}",algorithmic);
        environment.setVariable("algorithmic", new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build().wrap(algorithmic));
        if (templateDirectiveBody != null) {
            templateDirectiveBody.render(environment.getOut());
        }
    }
}
