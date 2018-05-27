package com.upms.service.user.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upms.domain.user.User;
import com.upms.domain.UserCreateForm;
import com.upms.repository.user.UserRepository;
import com.upms.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

	@Override
	public Optional<User> getUserById(long id) {
	    LOGGER.debug("Getting user by id={}", id);
		return userRepository.findById(id);
	}



	@Override
	public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email);
		return userRepository.findOneByEmail(email);
	}



	@Override
	public Collection<User> getAllUsers() {
	    LOGGER.debug("Getting all users");
		return userRepository.findAll();
	}



	@Override
	public User create(UserCreateForm form) {
	    User user = new User();
	    user.setEmail(form.getEmail());
	    user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
	    user.setRole(form.getRole());
		return userRepository.save(user);
	}

	@Override
	public Boolean deleteUser(Long id) {
		userRepository.deleteById(id);
		return true;
	}
}
