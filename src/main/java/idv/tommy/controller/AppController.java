package idv.tommy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {
    @GetMapping("hello")
    public String getadmins() {       
        return "Hello app Spring Security";
    }
}