package com.github.maquina1995.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.maquina1995.rest.dto.ExampleDto;

@RestController
public class BodyController {

	/**
	 * Adicionalmente a la forma que hemos visto en:
	 * {@link RequestController#many2(ExampleDto)} si añadimos la anotación
	 * {@link RequestBody} en vez de tener que pasar los campos del objeto como
	 * queryParams, lo pasaremos en el body como un Json
	 * <p>
	 * A tener en cuenta: Un get no puede tener body
	 * 
	 * @see {@link RequestController#many2(ExampleDto)}
	 */
	@PostMapping("/body")
	public String body(@RequestBody ExampleDto dto) {
		return "Nombre: " + dto.getName() + " Surname " + dto.getSurname();
	}

}
