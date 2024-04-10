package com.nns.thinner.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {

	private final String userId;

	private final String password;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void setRoles(String... roles) {
		List<GrantedAuthority> auths = new ArrayList<>(roles.length);
		for (String role : roles) {
			Assert.isTrue(!role.startsWith("ROLE_"),
				() -> role + " cannot start with ROLE_ (it is automatically added)");
			auths.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		authorities = auths;
	}

}
