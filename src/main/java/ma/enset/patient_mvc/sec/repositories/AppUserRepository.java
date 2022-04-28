package ma.enset.patient_mvc.sec.repositories;

import ma.enset.patient_mvc.sec.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);

}
