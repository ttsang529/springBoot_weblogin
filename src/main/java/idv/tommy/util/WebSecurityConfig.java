package idv.tommy.util;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		


//		http
//		.csrf().ignoringAntMatchers("/admin/****","/user/**","/app/**");
		http
		.csrf().disable()
		.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/app/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/user").permitAll() 
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
	}
	

////For Single user password
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
	 
	@Autowired 
    @Qualifier("dataSource")
    private DataSource dataSource;
	
	@Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled"
                + " from users where username=?")
            .authoritiesByUsernameQuery("select username, authority "
                + "from authorities where username=?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
	
	
////password(First method implement)
//	private static final String USER_ENCODED_PASSWORD  = "$2a$10$tjN.z9gCogdj80v3sHMWfezQ55nlrWNUSuqbI3Hv4dH0IVDRIW1rK";	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
//	    auth.inMemoryAuthentication()
//	        .passwordEncoder(passwordEncoder())
//	        .withUser("user").password(USER_ENCODED_PASSWORD).roles("USER")
//			.and()
//			.withUser("admin").password("USER_ENCODED_PASSWORD").roles("ADMIN","USER");;
//	}

////Other methods to study implement insert default date
//	@Bean
//	public UserDetailsService userDetailsService() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password = passwordEncoder.encode("password");
//		InMemoryUserDetailsManager  manager = new InMemoryUserDetailsManager();
//		manager.createUser( User.withUsername("user").password(password).roles("USER").build());
//		manager.createUser( User.withUsername("admin").password(password).roles("ADMIN","USER").build());
//		
//		return manager;
//	}
	


}
