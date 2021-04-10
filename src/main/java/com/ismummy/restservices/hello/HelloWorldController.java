package com.ismummy.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }


    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("Olalekan", "ismail", "Lagos");
    }
}
