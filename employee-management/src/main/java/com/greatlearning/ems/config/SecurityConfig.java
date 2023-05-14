package com.greatlearning.ems.config;

import com.greatlearning.ems.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
       // auth.inMemoryAuthentication().withUser("ayan").password(passwordEncoder().encode("password")).roles("ADMIN").and()
        //        .withUser("madhu").password(passwordEncoder().encode("user")).roles("USER");

    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailService(){
        return new UserDetailsServiceImpl();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().
                antMatchers("/user/role")
                .permitAll().antMatchers("/user/")
                .permitAll()
                .antMatchers("/ems/employees").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/ems/employee/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/ems/employee").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/ems/employee/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/ems/employee").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/ems/employee").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .and().httpBasic();
    }
}
