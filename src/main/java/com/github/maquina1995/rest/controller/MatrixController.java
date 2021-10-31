package com.github.maquina1995.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

	/**
	 * Las {@link MatrixVariable} son argumentos que podemos pasarlos como clave
	 * valor en nuestras url, podemos pasar de 1 a N argumentos separados por
	 * <code>;</code> como los ejemplos de acontinuación:
	 * <p>
	 * <li>http://localhost:8080/pathvariable/matrix/matrix=1</li>
	 * <li>http://localhost:8080/pathvariable/matrix/matrix=1;matrix=2</li>
	 * <p>
	 * Para poder usar esta forma de pasar argumentos a la url debemos deshabilitar
	 * el reemplazo automático de los <code>;</code>
	 * <p>
	 * Para ello hemos creado la clase de configuración:
	 * {@link com.github.maquina1995.rest.configuration.WebConfig}
	 * 
	 */
	@GetMapping("/{matrix}")
	public String matrix(@MatrixVariable String matrix) {
		return "Valor de la matriz: " + matrix;
	}

}
