package com.services.chambitas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Long>{
	
	Permission findPermissionByKeyPermission(String keyPermission);

	@Query(value = "SELECT count(*) FROM permission as up where up.id = :id", nativeQuery = true)
	long getPermissionsByUser(@Param("id") long id);
	
	@Query(value = "SELECT * FROM permission AS p WHERE p.key_permission LIKE %:key% AND p.description LIKE %:desc% ORDER BY p.key_permission ASC",nativeQuery = true)
	Page<Permission> findPermissionsW(@Param("key") String key,@Param("desc") String description ,Pageable pageable);
	
}
