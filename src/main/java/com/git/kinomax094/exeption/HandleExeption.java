package com.git.kinomax094.exeption;


import com.git.kinomax094.dto.DtoError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HandleExeption {


    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<DtoError> handle(RuntimeException e) {
        DtoError dtoError = new DtoError();
        if (e instanceof WrongNameException) {
            dtoError.setMessage(e.getMessage());
        }
        if (e instanceof WrongPatternException) {
            dtoError.setMessage(e.getMessage());
        }
        return new ResponseEntity<DtoError>(dtoError, HttpStatus.NOT_FOUND);
    }
}
