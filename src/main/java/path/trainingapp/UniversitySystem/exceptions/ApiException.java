package path.trainingapp.UniversitySystem.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiException {
    private final String msg;
    private Map<String,String> map = new HashMap<>();
    private final HttpStatus httpStatus;

    public ApiException(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
    public ApiException(String msg, Map<String,String> map, HttpStatus httpStatus) {
        this.msg = msg;
        this.map = map;
        this.httpStatus = httpStatus;
    }
}
