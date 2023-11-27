package portal.news.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Slf4j
@Service
public class LogService {
    public void generate(int count) {
        log.info("Start generating logs");
        LongStream.range(0, count)
                .forEach(i -> log.info("Log {}", i));
    }
}
