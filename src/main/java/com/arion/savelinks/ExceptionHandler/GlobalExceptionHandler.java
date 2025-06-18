package com.arion.savelinks.ExceptionHandler;

import com.arion.savelinks.Error.ErrorEntity;
import com.arion.savelinks.Exception.LinkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<?> handleLinkNotFoundException(LinkNotFoundException linkNotFoundException){
        ErrorEntity errorEntity=new ErrorEntity(linkNotFoundException.getMessage(),"Link not found for the given id");
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }
}
