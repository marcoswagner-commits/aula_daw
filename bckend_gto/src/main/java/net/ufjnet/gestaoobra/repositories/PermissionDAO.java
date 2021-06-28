package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
	


}
