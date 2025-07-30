package io.github.abbassizied;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler({ RuntimeException.class, NullPointerException.class })
	public ResponseEntity<Map<String, String>> handleSpecificExceptions(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(Map.of("error", "A runtime error occurred: " + ex.getMessage()));
	}
}