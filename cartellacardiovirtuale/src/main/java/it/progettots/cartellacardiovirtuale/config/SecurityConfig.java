package it.progettots.cartellacardiovirtuale.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import it.progettots.cartellacardiovirtuale.service.UtenteService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our user service
    @Autowired
    private UtenteService utenteService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); 
    }
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/medici/**").hasRole("ADMIN")
			.antMatchers("/pazienti/**").hasRole("MEDICO")
			.antMatchers("/files/**").hasAnyRole("MEDICO", "PAZIENTE")
			.antMatchers("/").hasAnyRole("ADMIN","PAZIENTE", "MEDICO")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	//beans
		//bcrypt bean definition
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
 
		//authenticationProvider bean definition
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(utenteService);
			auth.setPasswordEncoder(passwordEncoder()); 
			return auth;
		}
		@Override
		public void configure(WebSecurity  web) throws Exception {
			web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**","/assets/**");
		}
		@Bean
		public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		    StrictHttpFirewall firewall = new StrictHttpFirewall();
		    firewall.setAllowUrlEncodedSlash(true);    
		    return firewall;
		} 
		@Bean
		RequestRejectedHandler requestRejectedHandler() {
		   return new HttpStatusRequestRejectedHandler ();
		}
		
	}







