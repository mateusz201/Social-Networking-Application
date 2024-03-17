package pl.mateuszswiatek.socialnetworkingapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ApiException extends RuntimeException{
    private HttpStatus status;

    public ApiException(ApiExceptionReason reason){
        super(reason.getMessage());
        this.status = reason.getStatus();
    }
}
