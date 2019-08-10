package uz.google.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import uz.google.security.jwt.InvalidJwtAuthenticationException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {VehicleNotFoundException.class})
    public ResponseEntity vehicleNotFound(VehicleNotFoundException ex, WebRequest request){
        log.debug("handling VehicleNotFoundException...");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {InvalidJwtAuthenticationException.class})
    public  ResponseEntity invalidJwtAuthentication(InvalidJwtAuthenticationException ex, WebRequest request){
        log.debug("handling InvalidJwtAuthenticationException..");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
