package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.User;

public interface UserService {
	User saveUser(User elmt);
	User updateUser(User elmt);
	void deleteUser(User elmt);
	void deleteUserById(Long id);
	User getUserById(Long id);
	List<User> getAllUser();
	User findByUsername (String username);
}
