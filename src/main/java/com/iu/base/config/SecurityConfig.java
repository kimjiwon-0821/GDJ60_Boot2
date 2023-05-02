package com.iu.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.base.member.MemberService;
import com.iu.base.member.MemberSocialService;
import com.iu.base.security.UserLoginFailHandler;
import com.iu.base.security.UserLogoutHandler;
import com.iu.base.security.UserLogoutSuccessHandler;
import com.iu.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Autowired
	private UserLogoutHandler userLogoutHandler;
	
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;
	
	
	@Bean
	//public을 선언하면 default로 바꾸라는 메시지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/favicon/**")
				;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
				.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
					//URL과 권한 매칭
					.antMatchers("/").permitAll()
					.antMatchers("/member/join").permitAll()
					.antMatchers("/notice/add").hasRole("ADMIN")
					.antMatchers("/notice/update").hasRole("ADMIN")
					.antMatchers("/notice/delete").hasRole("ADMIN")
					.antMatchers("/notice/*").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/qna/add").hasAnyRole("ADMIN","MENAGER","MEMBER")
					//.anyRequest().authenticated()
					.anyRequest().permitAll()
					.and()
					.formLogin()
						.loginPage("/member/login")
						//.defaultSuccessUrl("/")
						.successHandler(new UserSuccessHandler())
						//.failureUrl("/member/login")
						.failureHandler(new UserLoginFailHandler())
						.permitAll()
					.and()
					.logout()
						.logoutUrl("/member/logout")
						//.logoutSuccessUrl("/")
						//.addLogoutHandler(userLogoutHandler)
						.logoutSuccessHandler(userLogoutSuccessHandler)
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
					.and()
					.oauth2Login()
						.userInfoEndpoint()
						.userService(memberSocialService)
//					.sessionManagement()
//						.maximumSessions(1) //최대 허용가능한 session의 수, -1의 경우 무제한
//						.maxSessionsPreventsLogin(false) //false : 이전 사용자 세션 만료 - ture: 새로운 사용자 인증 실패 
					;
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getpPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
