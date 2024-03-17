package pl.mateuszswiatek.socialnetworkingapp.exception;


import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException exception) {
        ApiExceptionResponse body = new ApiExceptionResponse(exception.getMessage());
        HttpStatus status = exception.getStatus();

        return new ResponseEntity<>(body, status);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiExceptionResponse body = new ApiExceptionResponse("Request body is not readable");
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiExceptionResponse body = new ApiExceptionResponse("Some parameters are not valid");
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiExceptionResponse body = new ApiExceptionResponse("Path variable not valid");
        return new ResponseEntity<>(body, BAD_REQUEST);
    }
}
