package ma.enset.patient_mvc.sec.service;

import ma.enset.patient_mvc.sec.entites.AppRole;
import ma.enset.patient_mvc.sec.entites.AppUser;

public interface SecurityService {

    AppUser saveNewUser(String username,String password, String verifyPassword);
    void addRoleToUser(String username,String roleName);
    AppRole saveNewRole(String roleName,String description);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
}
