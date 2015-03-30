package com.programmaniaks.training.unittest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmaniaks.training.unittest.entity.Greeting;
import com.programmaniaks.training.unittest.service.GreetingService;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private GreetingService service;

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public List<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	service.create(new Greeting(counter.incrementAndGet(),
                String.format(template, name)));
    	
        return service.findAll();
    }
}