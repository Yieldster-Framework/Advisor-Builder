package com.ddc.yieldster.jsonbuilder.exception;

import com.ddc.yieldster.jsonbuilder.response.ErrorResponse;
import com.ddc.yieldster.jsonbuilder.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

@Slf4j
public class ExceptionHelper {

    public static Function<Throwable, ResponseEntity<Response>> handleException = throwable -> {
        log.error("Unable to retrieve data", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong",
                throwable.getLocalizedMessage()));
    };
}
