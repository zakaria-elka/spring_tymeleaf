package ma.enset.patient_mvc.sec.service;

import javafx.print.Collation;
import ma.enset.patient_mvc.sec.entites.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SecurityService securityService;

    public UserDetailsServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AppUser appUser=securityService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities=new ArrayList();
        appUser.getAppRoles().forEach(role->{
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });

        User user=new User(appUser.getUsername(),appUser.getPassword(),authorities);
        return user;
    }
}
