package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    
    User findUserByTokenAndUsername(String token, String username);
           
    User findUserById(Long id);
  
    @Query(value = "SELECT * FROM user u WHERE u.username LIKE %:username% AND u.names LIKE %:names% AND u.surnames LIKE %:surnames%",nativeQuery = true)
	Page<User> searchByFilters(@Param("username") String username,@Param("names") String names,@Param("surnames") String surnames, Pageable pageable);
	
    @Query(value = "SELECT u FROM User u JOIN u.city c JOIN u.state s JOIN u.salary sl JOIN u.modalidadTrabajo m WHERE \n"
    		+ "c.valor LIKE %:city% AND s.clave LIKE %:state% AND sl.clave LIKE %:salary% AND m.clave LIKE %:mod% AND u.jobTitle LIKE %:job% \n"
    		+ "AND u.regBorrado = 0 AND u.profileCompleted = true AND u.publicProfile = true ORDER BY u.regDateCreated ASC")
	Page<User> searchByFiltersWEB(@Param("city") String city,@Param("state") String state,@Param("salary") String salary, @Param("job") String job, @Param("mod") String mod,  Pageable pageable);
	
     	
	@Query(value = "SELECT u.permissions FROM User as u WHERE u.username = ?1")
	List<Permission> getPermissionsByUserName(String username);
	
	
}

