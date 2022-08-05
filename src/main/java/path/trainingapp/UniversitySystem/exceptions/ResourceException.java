package path.trainingapp.UniversitySystem.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceException {
    private final String msg;
    private final HttpStatus httpStatus;

    public ResourceException(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
