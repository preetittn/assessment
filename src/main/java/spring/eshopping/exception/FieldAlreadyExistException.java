package spring.eshopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldAlreadyExistException extends RuntimeException {
    public FieldAlreadyExistException(String s) {
        super(s);
    }
}
