package com.github.maquina1995.rest.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.maquina1995.rest.dto.ExampleDto;

/**
 * Se han omitido los {@link ResponseEntity} para mayor comodidad
 * 
 * @author MaQuiNa1995
 *
 */
@RestController
@RequestMapping("/requestparam")
public class RequestController {

	/**
	 * {@link RequestParam} hace que los argumentos del método sean extraidos de la
	 * petición
	 * <p>
	 * De tal manera a este controller se le invocaría así:
	 * http://localhost:8080/requestparam/simple?name=MaQuiNa
	 * <p>
	 * Otra cosa interesante sobre los requestParam es que al cogerles de la url se
	 * codifican que quiere decir esto pues que la siguiente petición:
	 * http://localhost:8080/requestparam/simple?name=MaQuiNa+1995
	 * <p>
	 * Daría como resultado: MaQuiNa 1995 el <code>+</code> sería codificado como un
	 * espacio por ejemplo
	 * 
	 * @see Homólogo: {@link PathController#simple(String)}
	 */
	@GetMapping("/simple")
	public String simple(@RequestParam String name) {
		return name;
	}

	/**
	 * Podremos tener parámetros opcionales añadiendo la clausula
	 * <code>required = false</code> a la anotación
	 * <p>
	 * Al contrario que en el pathVariable aqui no hace falta definir los 2
	 * endpoints en la anotacion pero si que tendríamos las 2 siguientes
	 * posibilidades
	 * 
	 * <li>http://localhost:8080/requestparam/optional?name=MaQuiNa</li>
	 * <li>http://localhost:8080/requestparam/optional</li>
	 * <p>
	 * 
	 * @see Homólogo: {@link PathController#optional(String)}
	 */
	@GetMapping("/optional")
	public String optional(@RequestParam(required = false) String name) {
		return "Nombre: " + name;
	}

	/**
	 * Adicionalmente a lo del required podemos usar el parámetro {@link Optional}
	 * para hacer de un parámetro opcional
	 * 
	 * <li>http://localhost:8080/requestparam/optional2?name=MaQuiNa</li>
	 * <li>http://localhost:8080/requestparam/optional2</li>
	 * 
	 * @see Homólogo: {@link PathController#optional2(String)}
	 */
	@GetMapping("/optional2")
	public String optional2(@RequestParam(required = false) Optional<String> name) {
		return "Nombre: " + name;
	}

	/**
	 * Podemos simular el comportamiento de arriba añadiendo la cláusula
	 * <code>defaultValue = "valor por defecto"</code>
	 * <p>
	 * Aunque este es un poco básico ya que el optional nos da mas libertad por
	 * código para manejar el caso en el que no venga con valor el parámetro
	 * <p>
	 * Cabe recalcar que si usas esta clausula automáticamente se pone a false el
	 * required como es obvio
	 * 
	 * <li>http://localhost:8080/requestparam/optional3?name=MaQuiNa</li>
	 * <li>http://localhost:8080/requestparam/optional3</li>
	 * 
	 */
	@GetMapping("/optional3")
	public String optional3(@RequestParam(defaultValue = "PorDefecto") String name) {
		return "Nombre: " + name;
	}

	/**
	 * Otra forma de hacer parámetros opcionales es usar un mapa aunque tambien nos
	 * sirve para agrupar en un mapa muchos parámetros
	 * 
	 * <li>http://localhost:8080/requestparam/many3?name=MaQuiNa</li>
	 * <li>http://localhost:8080/requestparam/many3</li>
	 * 
	 * @since Spring 3.2
	 * 
	 * @see Homólogo: {@link PathController#many(String)}
	 */
	@GetMapping(value = "/many")
	public String many(@RequestParam Map<String, String> pathVariables) {

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
		String ageBorn = pathVariables.get("ageBorn");
		if (ageBorn != null) {
			int age = LocalDate.now()
			        .getYear() - Integer.valueOf(ageBorn);
			response = response + " y tienes: " + age;
		}
		return response;
	}

	/**
	 * Adicionalmente podemos crear un dto para conglomerar mas de 1 parámetro en un
	 * objeto y hacer que los campos del mismo sean parte de la url como
	 * requestParams
	 * <p>
	 * para este fin en el argumento del controller se le debe pasar un Dto en este
	 * caso {@link ExampleDto}
	 * 
	 * @see {@link BodyController#body(ExampleDto)}
	 * 
	 */
	@GetMapping("/many2")
	public String many2(ExampleDto dto) {
		return "Nombre: " + dto.getName() + " Surname " + dto.getSurname();
	}

}
