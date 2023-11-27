package portal.news.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestControlller {

    @GetMapping("/hello")
    public String sayHello(){
        return "sad";
    }
}
