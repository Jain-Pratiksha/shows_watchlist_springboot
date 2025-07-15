package com.shows_watchlist.exception;

import com.shows_watchlist.dto.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ShowWatchListExceptionhandler {

    private static final Logger logger = LoggerFactory.getLogger(ShowWatchListExceptionhandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException: {}", ex.getMessage(), ex.getCause());
        ShowWatchListResponseDTO showWatchListResponseDTO = new ShowWatchListResponseDTO();
        if (ex.getMessage().contains("Enum")) {
            logger.error("Enum value is not valid: {}", ex.getMessage());
            showWatchListResponseDTO.setStatus("Invalid enum value: " + ex.getMessage());
            return ResponseEntity.badRequest().body(showWatchListResponseDTO);
        }
        showWatchListResponseDTO.setStatus("Required request body is missing or unreadable: " + ex.getMessage());
        return ResponseEntity.badRequest().body(showWatchListResponseDTO);
    }

}
