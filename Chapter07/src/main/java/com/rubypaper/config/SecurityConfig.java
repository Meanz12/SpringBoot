package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //이 클래스로부터 생성도니 객체가 시큐리티 설정파일임을 의미, 시큐리티를 사용하는데 필요한 수많은 객체 생성
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;
	
    @Autowired 
    private BoardUserDetailsService boardUserDetailsService;
	
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().antMatchers("/").permitAll();
        security.authorizeRequests().antMatchers("/member/**").authenticated();
        security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        security.csrf().disable(); //RESTful을 사용하기 위해서는 비활성화 해야 한다.
        security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true); //스프링 기본 로그인 페이지 말고 사용자 정의 로그인 페이지 사용
        security.exceptionHandling().accessDeniedPage("/accessDenied");
        security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
        
        security.userDetailsService(boardUserDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
//    인증하지 않은 유저가 인증자들만 들어갈 수 있는 페이지를 요청하면 로그인 페이지로 이동
//    @Autowired
//    public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("manager").
//                password("{noop}manager123").
//                roles("MANAGER");
//
//        auth.inMemoryAuthentication().
//                withUser("admin").
//                password("{noop}admin123").
//                roles("ADMIN");
//    }
//  @Autowired
//  public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//      String query1 = "select id username, concat('{noop}', password) password, true enabled from member where id=?";
//      String query2 = "select id, role from member where id=?";
//
//      auth.jdbcAuthentication()
//              .dataSource(dataSource)
//              .usersByUsernameQuery(query1)
//              .authoritiesByUsernameQuery(query2);
//  }
    

}