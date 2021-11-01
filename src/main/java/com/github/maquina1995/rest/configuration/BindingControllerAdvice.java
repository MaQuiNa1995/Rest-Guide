package com.github.maquina1995.rest.configuration;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Si no indicamos un controller en {@link RestControllerAdvice} la
 * configuración que pongas en esta clase se aplica a todos los controller
 * globalmente
 * 
 * @author MaQuiNa1995
 *
 */
@RestControllerAdvice
public class BindingControllerAdvice {

	/**
	 * Con este método indicamos a spring que debe usar reflection para setear los
	 * campos cuando se tenga en un endpoint un dto por ejemplo sin el
	 * {@link org.springframework.web.bind.annotation.ResponseBody}
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

}
