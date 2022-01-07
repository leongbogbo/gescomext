package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role saveRole(Role elmt) {
		return roleRepository.save(elmt);
	}

	@Override
	public Role updateRole(Role elmt) {
		return roleRepository.save(elmt);
	}

	@Override
	public void deleteRole(Role elmt) {
		roleRepository.delete(elmt);		
	}

	@Override
	public void deleteRoleById(Long id) {
		roleRepository.deleteById(id);		
	}

	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).get();
	}

	@Override
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

}
