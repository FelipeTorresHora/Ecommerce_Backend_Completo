package felipe.proj.ecombackend.repositorio;

import felipe.proj.ecombackend.model.Role;
import felipe.proj.ecombackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepositorio userRepositorio;
    private final RoleRepositorio roleRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles =  Set.of("ROLE_ADMIN", "ROLE_USER");
        createDefaultUserIfNotExits();
        createDefaultRoleIfNotExits(defaultRoles);
        createDefaultAdminIfNotExits();
    }


    private void createDefaultUserIfNotExits(){
        Role userRole = roleRepositorio.findByNome("ROLE_USER").get();
        for (int i = 1; i<=5; i++){
            String defaultEmail = "sam"+i+"@email.com";
            if (userRepositorio.existsByEmail(defaultEmail)){
                continue;
            }
            User user = new User();
            user.setFirstName("Padrao");
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setSenha(passwordEncoder.encode("12345678A"));
            user.setRoles(Set.of(userRole));
            userRepositorio.save(user);
            System.out.println("Default vet user " + i + " created successfully.");
        }
    }



    private void createDefaultAdminIfNotExits(){
        Role adminRole = roleRepositorio.findByNome("ROLE_ADMIN").get();
        for (int i = 1; i<=2; i++){
            String defaultEmail = "admin"+i+"@email.com";
            if (userRepositorio.existsByEmail(defaultEmail)){
                continue;
            }
            User user = new User();
            user.setFirstName("Padrao");
            user.setLastName("Admin" + i);
            user.setEmail(defaultEmail);
            user.setSenha(passwordEncoder.encode("12345678A"));
            user.setRoles(Set.of(adminRole));
            userRepositorio.save(user);
            System.out.println("Default admin user " + i + " created successfully.");
        }
    }
    private void createDefaultRoleIfNotExits(Set<String> roles){
        roles.stream()
                .filter(role -> roleRepositorio.findByNome(role).isEmpty())
                .map(Role:: new).forEach(roleRepositorio::save);
    }
}
