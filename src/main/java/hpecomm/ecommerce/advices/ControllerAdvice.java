package hpecomm.ecommerce.advices;

import hpecomm.ecommerce.DTO.ErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException notFoundException){
        return new ResponseEntity<>(
                new ErrorDto(notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
