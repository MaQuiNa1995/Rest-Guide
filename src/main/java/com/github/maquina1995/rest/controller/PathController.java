package com.github.maquina1995.rest.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Se han omitido los {@link ResponseEntity} para mayor comodidad
 * 
 * Una cosa a tener en cuenta con el uso del path variable es que los valores
 * extraidos de la url siempre son Strings por lo tanto si viene un
 * {@link Integer} tendremos que castearlo a desde {@link String} a
 * {@link Integer} y asi con todos los tipos
 * 
 * @author MaQuiNa1995
 *
 */
@RestController
@RequestMapping("/pathvariable")
public class PathController {

	/**
	 * {@link PathVariable} hace que los argumentos del método sean extraidos de la
	 * URL
	 * <p>
	 * De tal manera a este controller se le invocaría así:
	 * http://localhost:8080/pathvariable/simple/MaQuiNa
	 * <p>
	 * En este caso es necesario indicar en la anotación {@link GetMapping} el
	 * parámetro y en el caso en el que no concuerde el nombre de la variable con el
	 * nombre de la url que pongamos en el path, deberemos usar
	 * <code>@PathVariable(name = "nombreUrl")</code>
	 * <p>
	 * Al contrario que requestparam aqui al pasar la siguiente petición:
	 * http://localhost:8080/pathparam/simple?name=MaQuiNa+1995 el <code>+</code> no
	 * sería codificado y se cogería la string literal es decir que daria como
	 * resultado: MaQuiNa+1995
	 * 
	 * @see Homólogo: {@link RequestController#simple(String)}
	 */
	@GetMapping("/simple/{name}")
	public String simple(@PathVariable(name = "name") String name) {
		return name;
	}

	/**
	 * 
	 * 
	 * Podremos tener parámetros opcionales añadiendo la clausula
	 * <code>required = false</code> a la anotación de tal manera que tendremos 2
	 * paths para el mismo endpoint uno con parámetro y otro sin
	 * 
	 * <li>http://localhost:8080/pathparam/optional/MaQuiNa</li>
	 * <li>http://localhost:8080/pathparam/optional</li>
	 * <p>
	 * 
	 * Cuando usemos el required false debemos tener cuidado con el tema de
	 * conflictos de endpoints que en tal caso nos saldrá un error del tipo a
	 * <code>Ambiguous mapping</code>
	 * 
	 * @since Lo del required está desde la versión de Spring 4.3.3
	 * 
	 * @see Homólogo: {@link RequestController#optional(String)}
	 */
	@GetMapping({ "/optional", "/optional/{name}" })
	public String optional(@PathVariable(required = false) String name) {
		return "Nombre: " + name;
	}

	/**
	 * Adicionalmente a lo del required podemos usar el parámetro {@link Optional}
	 * para hacer de un parámetro opcional
	 * 
	 * <li>http://localhost:8080/requestparam/optional2/MaQuiNa</li>
	 * <li>http://localhost:8080/requestparam/optional2</li>
	 * 
	 * @since Jdk 8 y Spring 4.1
	 */
	@GetMapping({ "/optional2", "/optional2/{name}" })
	public String optional2(@PathVariable Optional<String> name) {
		return "Nombre: " + name.orElse("PorDefecto");
	}

	/**
	 * Otra forma de hacer parámetros opcionales es usar un mapa aunque tambien nos
	 * sirve para agrupar en un mapa muchos parámetros
	 * <p>
	 * <li>http://localhost:8080/requestparam/many3/MaQuiNa/1995</li>
	 * <li>http://localhost:8080/requestparam/many3</li>
	 * 
	 * @since Spring 3.2
	 * @see Homólogo: {@link RequestController#many(String)}
	 */
	@GetMapping(value = { "/many", "/many/{name}/{bornAge}" })
	public String many(@PathVariable Map<String, String> pathVariables) {

		// En la key del mapa vendrán los nombres de la variables

		String response = "";

		// de tal manera que al hacer un get y un if podremos saber si este tiene valor
		// o no
		String name = pathVariables.get("name");
		if (name != null) {
			response = "Te llamas " + name;
		}

		// Como dijimos antes al recoger el valor del path se cogen como String por lo
		// tanto si queremos usar números por ejemplo nos tocará hacer un parse
		String bornAge = pathVariables.get("bornAge");
		if (bornAge != null) {
			int age = LocalDate.now()
			        .getYear() - Integer.valueOf(bornAge);
			response = response + " y tienes: " + age;
		}
		return response;
	}
}
