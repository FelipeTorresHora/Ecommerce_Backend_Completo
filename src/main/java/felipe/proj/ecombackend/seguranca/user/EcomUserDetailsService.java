package felipe.proj.ecombackend.seguranca.user;

import felipe.proj.ecombackend.model.User;
import felipe.proj.ecombackend.repositorio.UserRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EcomUserDetailsService implements UserDetailsService {
    private final UserRepositorio userRepositorio;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepositorio.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return EcomUserDetails.buildUserDetails(user);
    }
}
