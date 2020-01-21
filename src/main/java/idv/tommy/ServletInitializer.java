package idv.tommy;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password = passwordEncoder.encode("password");
//		System.out.println("Tommy password:"+password);
		return application.sources(WebloginApplication.class);
	}

}
