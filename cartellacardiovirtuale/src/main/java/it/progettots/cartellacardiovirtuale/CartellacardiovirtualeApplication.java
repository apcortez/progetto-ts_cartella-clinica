package it.progettots.cartellacardiovirtuale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import it.progettots.cartellacardiovirtuale.config.ThymeleafConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
public class CartellacardiovirtualeApplication  extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(CartellacardiovirtualeApplication.class, args);
	}
	
	@Bean
    public ClassLoaderTemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    } 
	 @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	        registry.addResourceHandler("/resources/**")
	          .addResourceLocations("templates/"); 
	        super.addResourceHandlers(registry);
	    }
}
