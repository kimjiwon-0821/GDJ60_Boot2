package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO implements UserDetails{
	@NotBlank
	private String username;
	@NotBlank
	@Size(min=3,max=20)
	private String password;
	private String passwordCheck;
	@NotBlank
	private String name;
	@NotBlank 
	@Email
	private String email;
	@Past
	private Date birth;
	
	private Date lastTime;
	
	private List<RoleVO> roleVOs;
	
	private Boolean enabled;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO:roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		return authorities;
	}
	
//   @Override
//	private void getusername() {
//		// TODO Auto-generated method stub
//		username(id) 반환
//	}
	
//  @Override
//	private void getpassword() {
//		// TODO Auto-generated method stub
//		password(pw) 반환
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		//계정의 만료 여부
		//true : 만료 안됨 
		//false: 만료 됨 - 로그인 안됨
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		//계정 잠김 여부 ex)비번 5번이상 오류 시 계정 잠김
		//true : 잠기지 않음
		//flase: 잠김 - 로그인 안됨
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		//password 만료 여부
		//true : 만료 안됨
		//false: 만료 됨 - 로그인 안됨
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		//계정 사용 가능 여부
		//true : 계정 활성화
		//false: 계정 비활성화 - 로그인 안됨
		return this.enabled;
	}

	

	
	

}
