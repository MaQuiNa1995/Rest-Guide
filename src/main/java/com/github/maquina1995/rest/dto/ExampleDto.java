package com.github.maquina1995.rest.dto;

import com.github.maquina1995.rest.configuration.BindingControllerAdvice;

import lombok.Getter;

/**
 * En este dto Se han omitido los setter para hacer inmutable el Dto, para que
 * spring pueda "setear" los valores se debe añadir una config global para todos
 * los controller
 * {@link BindingControllerAdvice#initBinder(org.springframework.web.bind.WebDataBinder)}
 * 
 * @author MaQuiNa1995
 *
 */
@Getter
public class ExampleDto {

	private String name;
	private String surname;
}
