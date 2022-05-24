package io.yieldster.sampleProject.exception;

import io.yieldster.sampleProject.response.ErrorResponse;
import io.yieldster.sampleProject.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public class ExceptionHelper {

    public static Function<Throwable, ResponseEntity<Response>> handleException = throwable -> {
//        log.error("Unable to retrieve data", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong",
                throwable.getLocalizedMessage()));
    };
}
