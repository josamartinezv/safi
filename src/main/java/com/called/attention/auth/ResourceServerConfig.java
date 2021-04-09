package com.called.attention.auth;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user/account").hasAnyRole("APPRENTICE", "INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/api/usersHasProgramNumber/search-by-programNumber").hasAnyRole( "INSTRUCTOR", "APPRENTICE")
                .antMatchers(HttpMethod.GET, "/api/usersHasProgramNumber/search-by-documentNumber").hasAnyRole( "INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/api/verbalCalled/search-by-filters").hasAnyRole("INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/api/verbalCalled").hasAnyRole("INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/api/verbalCalled/search-by-documentNumber").hasAnyRole("INSTRUCTOR", "APPRENTICE")
                .antMatchers(HttpMethod.POST, "/api/verbalCalled").hasAnyRole("APPRENTICE", "INSTRUCTOR")
                .anyRequest().permitAll();
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corseFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(configurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


}
