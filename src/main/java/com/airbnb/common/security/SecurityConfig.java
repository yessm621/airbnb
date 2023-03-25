package com.airbnb.common.security;

import com.airbnb.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/members/*").permitAll()
                .antMatchers("/image/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .defaultSuccessUrl("/")
                .failureUrl("/members/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .loginPage("/members/login");
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/members/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me");
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/members/login?error=true&exception=로그인 세션이 만료되었습니다. 다시 로그인해주세요.");
        http.rememberMe()
                .alwaysRemember(false)
                .tokenValiditySeconds(43200)
                .rememberMeParameter("remember-me");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthSuccessHandler(memberRepository);
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthFailureHandler();
    }
}
