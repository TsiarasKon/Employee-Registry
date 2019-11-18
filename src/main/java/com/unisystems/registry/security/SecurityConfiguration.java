package com.unisystems.registry.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("companyManager").password(passwordEncoder().encode("123456")).roles("COMPANY_MANAGER")
                .and()
                .withUser("businessUnitManager").password(passwordEncoder().encode("123456")).roles("BUSINESS_MANAGER")
                .and()
                .withUser("departmentManager").password(passwordEncoder().encode("123456")).roles("DEPARTMENT_MANAGER")
                .and()
                .withUser("unitManager").password(passwordEncoder().encode("123456")).roles("UNIT_MANAGER")
                .and()
                .withUser("employee").password(passwordEncoder().encode("123456")).roles("EMPLOYEE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest().authenticated
                .antMatchers("/Companies/**").hasAnyRole("ADMIN","COMPANY_MANAGER")
                .antMatchers("/BusinessUnits/**").hasAnyRole("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER")
                .antMatchers("/Departments/**").hasAnyRole("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER")
                .antMatchers("/Units/**").hasAnyRole("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER","UNIT_MANAGER")
                .antMatchers("/Employees/**").hasAnyRole("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER","UNIT_MANAGER","EMPLOYEE")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
