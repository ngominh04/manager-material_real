package vn.com.devmaster.service.managermaterial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                // những đường dẫn trước và sau view (authenticated: đăng nhập là vào được   hasAnyRole: phân quyền)
                .antMatchers("/view/dell").hasAnyRole("admin")
                // đường dẫn được tất cả các quyền (thường để cuối vì duyệt toàn bộ)
                .antMatchers("/**").permitAll()
                .and().formLogin()
//                .loginProcessingUrl("/login_check")
                .loginPage("/view/login");
        return httpSecurity.build();
    }
}
