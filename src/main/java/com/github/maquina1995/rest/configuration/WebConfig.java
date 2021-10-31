package com.github.maquina1995.rest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * Si necesitamos usar
 * {@link org.springframework.web.bind.annotation.MatrixVariable} debemos crear
 * esta clase de configuración para habilitarlas, por defecto están
 * {@literal false}
 * <p>
 * Lo conseguiremos con el siguiente método:
 * {@link WebConfig#configurePathMatch(PathMatchConfigurer)}
 * 
 * @author MaQuiNa1995
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Con este método deshabilitaremos el reemplazo automático de <code>;</code> en
	 * las URL y por ende habilitaremos las
	 * {@link org.springframework.web.bind.annotation.MatrixVariable} variables
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);

		configurer.setUrlPathHelper(urlPathHelper);
	}
}
