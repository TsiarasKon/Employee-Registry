package com.unisystems.registry.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username,password, enabled from login_user where username=?")
                .authoritiesByUsernameQuery(
                        "select username,role from authority where username =?");
    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        jdbcUserDetailsManager.setDataSource(dataSource);
//        return jdbcUserDetailsManager;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll();
//                .antMatchers("/Units/list").hasAnyAuthority("ADMIN","COMPANY_MANAGER")
//                .antMatchers("/Companies/list").hasAnyAuthority("ADMIN","COMPANY_MANAGER")
//                .antMatchers("/BusinessUnits/list").hasAnyAuthority("ADMIN", "COMPANY_MANAGER")
//                .antMatchers("/Departments/list").hasAnyAuthority("DEPARTMENT_MANAGER","ADMIN")
                //.antMatchers("/employees").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER')")
//                .antMatchers("/departments").hasAnyRole("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER")
//                .antMatchers("/Units/**").hasAnyAuthority("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER","UNIT_MANAGER")
                //.antMatchers("/employees").hasAnyAuthority("ADMIN","COMPANY_MANAGER")
//                .antMatchers("/employees").hasAnyAuthority("ADMIN","COMPANY_MANAGER","BUSINESS_MANAGER","DEPARTMENT_MANAGER","UNIT_MANAGER")
//                .and()
//                .httpBasic()
//                    ; //userdetails interface!
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/");
    }
}
