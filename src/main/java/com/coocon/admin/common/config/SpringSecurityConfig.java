package com.coocon.admin.common.config;


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
public class SpringSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http   .csrf().disable() //browser로부터 직접 받는게 아닐경우
                .headers().frameOptions().sameOrigin()
                .and().logout().logoutSuccessUrl("/");
        http.cors();

        http    .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션으로 진행하지 않음

        // TODO DB에서 권한별 화면 조정등에 대한 것을 불러와서 자동으로 넣어주도록 수정
        http    .authorizeRequests()
                .antMatchers("/register/*","/oauth2/**","/login/*","/oauth/**","/h2-console/**","/error","/login").permitAll()
                .antMatchers("/dashboard/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated(); //permit한 리소스 제외 접근 시 인증 필요

        http.oauth2Login()
                .loginPage("http://localhost:3000/login")
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and().successHandler(oAuth2SuccessHandler)
                .and().logout().logoutSuccessUrl("http://localhost:3000/login");
                //로그인 성공시 서비스

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
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