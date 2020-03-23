package org.example.springsecurity.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        Set in-memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("foo")
//                .password("foo")
//                .roles("USER")
//                .and()
//                .withUser("wang")
//                .password("wang")
//                .roles("ADMIN");

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
////                customize which table the users and authorities should be selected from
//                .usersByUsernameQuery("select username,password,enabled "
//                    + "from users "
//                    + "where username = ?")
//                .authoritiesByUsernameQuery("select username,authority "
//                    + "from authorities "
//                    + "where username = ?");
        auth.userDetailsService(userDetailsService);


    }

    @Bean
    public PasswordEncoder getPasswordEncoded() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }



}
