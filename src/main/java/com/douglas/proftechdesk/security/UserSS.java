package com.douglas.proftechdesk.security;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.douglas.proftechdesk.domain.enums.Profile;

public class UserSS implements UserDetails {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final Integer id;
	private final String email;
	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;

	public UserSS(Integer id, String email, String password, Set<Profile> profiles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDesc())).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
