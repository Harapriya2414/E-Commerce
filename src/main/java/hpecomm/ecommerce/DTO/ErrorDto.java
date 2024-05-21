package hpecomm.ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String message;
    public ErrorDto(String message){
        this.message = message;
    }
}
