package br.unipe.hotel;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class HotelApplication extends WebMvcAutoConfigurationAdapter{

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	
	/* Corrige a data, casas decimais separadas com vírgula, etc */
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/hospedes");
	}
	
}
