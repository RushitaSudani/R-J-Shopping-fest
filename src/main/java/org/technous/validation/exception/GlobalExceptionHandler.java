package org.technous.validation.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.technous.validation.util.ApiResponse;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourseNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> resp= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String Filedname=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            resp.put(Filedname,message);
    });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidInput(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Invalid request payload.");
    }


}
