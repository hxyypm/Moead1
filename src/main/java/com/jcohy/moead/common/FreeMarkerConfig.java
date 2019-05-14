package com.jcohy.moead.common;

import com.jcohy.moead.directive.AlgorithmicDirective;
import com.jcohy.moead.directive.ResourceDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;


    @Autowired
    private ResourceDirective resourceDirective;

    @Autowired
    private AlgorithmicDirective algorithmicDirective;

    @PostConstruct
    public void setSharedVariable() {
    	try {
			configuration.setSharedVariable("resourceList", resourceDirective);
            configuration.setSharedVariable("algorithmic", algorithmicDirective);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
