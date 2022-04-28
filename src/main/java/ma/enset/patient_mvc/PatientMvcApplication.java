package ma.enset.patient_mvc;

import ma.enset.patient_mvc.entites.Patient;
import ma.enset.patient_mvc.repositories.PatientRepositories;
import ma.enset.patient_mvc.sec.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {
    //Declaration de l'objet

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepositories patientRepositories){
        return args ->{
            patientRepositories.save(new Patient(null,"Hassan",new Date(),false,100));
            patientRepositories.save(new Patient(null,"Mohamed",new Date(),true,120));
            patientRepositories.save(new Patient(null,"Yasemine",new Date(),false,130));

            patientRepositories.findAll().forEach(patient -> {
                        System.out.println(patient.getNom());

                    }
            );
        };
    }
    //@Bean
   /* CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {

            securityService.saveNewUser("zakaria","1234","1234");
            securityService.saveNewRole("ADMIN","");
            securityService.addRoleToUser("zakaria","ADMIN");



        };
    }*/
}