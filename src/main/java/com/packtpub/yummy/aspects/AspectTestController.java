package com.packtpub.yummy.aspects;

import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aspecttest")
public class AspectTestController {
    @Autowired @Lazy
    BeanCounter beanCounter;

    @RequestMapping
    public BeanCounter execute() throws Exception {
        beanCounter.counter++;
        Advised adv=(Advised)beanCounter;
        return (BeanCounter) adv.getTargetSource().getTarget();
    }
}
