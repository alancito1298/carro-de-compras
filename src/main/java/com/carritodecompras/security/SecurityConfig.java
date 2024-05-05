package com.carritodecompras.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean 
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
		
		httpSecurity.authorizeRequests(authorize -> authorize
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/carritodecompras/", "/carritodecompras/inicio", "/carritodecompras/registro").permitAll()
                .antMatchers("/carritodecompras/principal/").hasAnyAuthority("Cliente", "Vendedor")
                .antMatchers("/carritodecompras/cliente/**").hasAuthority("Cliente")
                .antMatchers("/carritodecompras/vendedor/**").hasAuthority("Vendedor")
                .antMatchers("/carritodecompras/", "/carritodecompras/inicio", "/carritodecompras/registro").anonymous()
                .anyRequest().authenticated()
        )
		
		.formLogin(l-> l
				.loginPage("/carritodecompras/inicio") 
				.permitAll());
		
		return httpSecurity.build();
	}
	
	
	/**
	 * 
	 * @return el bean configurado para recuperar las contraseñas encriptadas.
	 */
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
}
