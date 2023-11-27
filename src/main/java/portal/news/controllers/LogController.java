package portal.news.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import portal.news.services.LogService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService service;

    @GetMapping("/generate")
    public ResponseEntity test(@RequestParam(name = "count", defaultValue = "0") Integer count) {
        log.info("Test request received with count: {}", count);
        service.generate(count);
        return ResponseEntity.ok("Success!");
    }
}
