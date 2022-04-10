package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername (String username);
	List<User> findBynomUserOrPrenomsUserContaining (String nom,String prenoms);
	List<User> findBySite(Site site);
}