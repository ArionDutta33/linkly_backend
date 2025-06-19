package com.arion.savelinks.ExceptionHandler;

import com.arion.savelinks.Error.ErrorEntity;
import com.arion.savelinks.Exception.LinkNotFoundException;
import com.arion.savelinks.Exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<?> handleLinkNotFoundException(LinkNotFoundException linkNotFoundException){
        ErrorEntity errorEntity=new ErrorEntity(linkNotFoundException.getMessage(),"Link not found for the given id");
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }
@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        ErrorEntity errorEntity=new ErrorEntity(usernameNotFoundException.getMessage(),"User is not available in the database");
        return new ResponseEntity<>(errorEntity,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        ErrorEntity errorEntity = new ErrorEntity("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(
                new ErrorEntity("Validation failed", String.join(", ", errors)),
                HttpStatus.BAD_REQUEST
        );
    }


}
