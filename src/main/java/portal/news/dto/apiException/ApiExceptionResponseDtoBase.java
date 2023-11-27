package portal.news.dto.apiException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Getter
@AllArgsConstructor
public abstract class ApiExceptionResponseDtoBase {

    protected final int errorCode;
    protected final HttpStatus httpStatus;
    protected final ZonedDateTime timestamp;

}
