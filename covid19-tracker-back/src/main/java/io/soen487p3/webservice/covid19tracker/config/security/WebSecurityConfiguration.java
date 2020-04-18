package io.soen487p3.webservice.covid19tracker.config.security;


import io.soen487p3.webservice.covid19tracker.error.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomLoginSuccessHandler sucessHandler;

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/home").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/superuser/**").hasRole("SUPER_USER")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN_USER","SUPER_USER")
                    .antMatchers("/user/**").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .antMatchers("/worldsta").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .antMatchers("/globalmap").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .antMatchers("/countrysta").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .antMatchers("/canada").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .antMatchers("/affectedcountries").hasAnyRole("SUPER_USER","ADMIN_USER", "SITE_USER")
                    .anyRequest().authenticated()
                    .and()

//                  formLogin(); //below is to activate custom form login
                    .csrf().disable().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .successHandler(sucessHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                    // logout
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied");
    }

    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
