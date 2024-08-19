package uno.fastcampus.testdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // 모든 시큐리티 인증설정을 열어두기
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            // 로그아웃하면 초기화면으로 돌아가기
            .logout(logout -> logout.logoutSuccessUrl("/"))
            .build();
    }
}
