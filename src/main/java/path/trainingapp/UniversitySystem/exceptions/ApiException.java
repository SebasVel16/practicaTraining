package path.trainingapp.UniversitySystem.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {
    private final String msg;
    private final HttpStatus httpStatus;

    public ApiException(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
