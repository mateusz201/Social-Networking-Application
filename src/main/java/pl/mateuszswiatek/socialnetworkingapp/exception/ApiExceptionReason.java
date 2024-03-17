package pl.mateuszswiatek.socialnetworkingapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@AllArgsConstructor
@Getter
public enum ApiExceptionReason {
    USERNAME_TAKEN("Username is already taken", CONFLICT),
    EMAIL_TAKEN("Email is already taken", CONFLICT);

    private final String message;
    private final HttpStatus status;
}
