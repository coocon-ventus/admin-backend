package com.coocon.admin.common.config;


import com.coocon.admin.security.filter.JwtExceptionHandleFilter;
import com.coocon.admin.security.service.CustomOAuth2UserService;
import com.coocon.admin.security.handler.OAuth2SuccessHandler;
import com.coocon.admin.security.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthFilter jwtAuthFilter;
    private  final JwtExceptionHandleFilter jwtExceptionHandleFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // TODO Spring 2.7 -> 3.0.2 로 올라가며 spring 6에서 csrf 토큰에 대한 내용이 바뀌어 spring security가 정상동작하지 않는 상황
        http   .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and().logout().logoutSuccessUrl("/");
        http.cors();

        http    .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션으로 진행하지 않음

        // TODO DB에서 권한별 화면 조정등에 대한 것을 불러와서 자동으로 넣어주도록 수정
        http.authorizeHttpRequests()
                .antMatchers("/register/*","/oauth2/**","/login/*","/oauth/**","/h2-console/**","/error","/login").permitAll()
                .antMatchers("/dashboard/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated(); //permit한 리소스 제외 접근 시 인증 필요

        http.oauth2Login()
                .loginPage("http://test.co.kr").disable().oauth2Login()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and().successHandler(oAuth2SuccessHandler)
                .and().logout().logoutSuccessUrl("http://localhost:3000/login")
                        .and().formLogin().disable();
                //로그인 성공시 서비스

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionHandleFilter, JwtAuthFilter.class);

        return http.build();
    }

    //cors 접속 허용
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
