package io.github.abbassizied;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception ex) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("errorMessage", ex.getMessage());
		return model;
	}

	@ExceptionHandler({ RuntimeException.class, NullPointerException.class })
	public ModelAndView handleSpecificExceptions(Exception ex) {
		ModelAndView model = new ModelAndView("error-specific");
		model.addObject("errorMessage", "A runtime error occurred: " + ex.getMessage());
		return model;
	}
}