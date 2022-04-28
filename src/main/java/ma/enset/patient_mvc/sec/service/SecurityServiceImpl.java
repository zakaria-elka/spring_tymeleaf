package ma.enset.patient_mvc.sec.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.patient_mvc.sec.entites.AppRole;
import ma.enset.patient_mvc.sec.entites.AppUser;
import ma.enset.patient_mvc.sec.repositories.AppRoleRepository;
import ma.enset.patient_mvc.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor

public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser saveNewUser(String username, String password, String verifyPassword) {
        if(!password.equals(verifyPassword)) throw  new RuntimeException("Password Not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);

        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("Role"+roleName+"Already exist");
        appRole =new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);

        return savedAppRole;
    }




    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);


    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
