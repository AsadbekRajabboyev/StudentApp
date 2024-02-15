/******************************************************************************
 * Copyright (c)  2/15/24, 9:17 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import uz.asadbek.student.app.securityService.UserDetailsService;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final UserDetailsService service;
	@Autowired
	public SecurityConfig(UserDetailsService service) {
		this.service = service;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(configurer->
						configurer
								.requestMatchers("/api/admin/**").hasRole("ADMIN")
								.requestMatchers("/api/auth/**","/error")
								.permitAll()
								.anyRequest().hasAnyRole("ADMIN", "USER")
				)
				.formLogin(form ->
						form
								.loginPage("api/auth/login")
								.usernameParameter("username")
								.passwordParameter("password")
								.loginProcessingUrl("/process_login")
								.defaultSuccessUrl("/home", true)
								.failureUrl("/auth/login?error")
								.permitAll()
				)
				.logout(logout ->
						logout
								.logoutUrl("/logout")
								.logoutSuccessUrl("api/auth/login"))
				.exceptionHandling(exeption->
						exeption
								.accessDeniedHandler(accessDeniedHandler()));
		return http.build();
	}
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (httpServletRequest, httpServletResponse, e) -> {
			httpServletResponse.sendRedirect("admin/access-denied");
		};
	}
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
				httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(service).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}
}

