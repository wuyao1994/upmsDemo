package com.upms.service.user;

import java.util.Collection;
import java.util.Optional;

import com.upms.domain.user.User;
import com.upms.domain.UserCreateForm;

public interface UserService {
	Optional<User> getUserById(long id);



	Optional<User> getUserByEmail(String email);



	Collection<User> getAllUsers();



	User create(UserCreateForm form);

	Boolean deleteUser(Long id);
}
