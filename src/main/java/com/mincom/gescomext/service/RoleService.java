package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Role;

public interface RoleService {
	Role saveRole(Role elmt);
	Role updateRole(Role elmt);
	void deleteRole(Role elmt);
	void deleteRoleById(Long id);
	Role getRoleById(Long id);
	List<Role> getAllRole();
}
