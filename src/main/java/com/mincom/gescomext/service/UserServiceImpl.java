package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User elmt) {
		return userRepository.save(elmt);
	}

	@Override
	public User updateUser(User elmt) {
		return userRepository.save(elmt);
	}

	@Override
	public void deleteUser(User elmt) {
		userRepository.delete(elmt);		
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);		
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findBynomUserOrPrenomsUserContaining (String nom,String prenoms) {
		return userRepository.findBynomUserOrPrenomsUserContaining(nom,prenoms);
	}

	@Override
	public List<User> findBySite(Site site) {
		return userRepository.findBySite(site);
	}

}
