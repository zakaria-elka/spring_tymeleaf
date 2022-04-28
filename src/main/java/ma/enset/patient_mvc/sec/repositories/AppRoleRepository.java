package ma.enset.patient_mvc.sec.repositories;

import ma.enset.patient_mvc.sec.entites.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
