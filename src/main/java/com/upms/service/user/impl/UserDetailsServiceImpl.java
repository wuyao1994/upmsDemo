package com.upms.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.upms.domain.CurrentUser;
import com.upms.domain.user.User;
import com.upms.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserService					userService;



	@Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.debug("Authenticating user with email={}", email);
		User user = userService.getUserByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email=% is not fonud", email)));
		return new CurrentUser(user);
	}
}
