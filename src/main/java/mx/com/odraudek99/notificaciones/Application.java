package mx.com.odraudek99.notificaciones;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import twitter4j.TwitterException;

@Configuration
@ComponentScan
@PropertySource({ "classpath:/twitter.properties", "file:/home/odraudek99/twittercta.properties" })
public class Application {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	TwitterInegration twitterInegration;

	public static void main(String[] args) throws IOException, TwitterException {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

	}

}
