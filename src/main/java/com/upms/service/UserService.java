package com.upms.service;

import java.util.Collection;
import java.util.Optional;

import com.upms.domain.User;
import com.upms.domain.UserCreateForm;

public interface UserService {
	Optional<User> getUserById(long id);



	Optional<User> getUserByEmail(String email);



	Collection<User> getAllUsers();



	User create(UserCreateForm form);
}
