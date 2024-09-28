package felipe.proj.ecombackend.service.user;

import felipe.proj.ecombackend.excecao.ExistenteException;
import felipe.proj.ecombackend.excecao.ProcuraNaoEncontrada;
import felipe.proj.ecombackend.model.User;
import felipe.proj.ecombackend.repositorio.UserRepositorio;
import felipe.proj.ecombackend.request.CreateUserRequest;
import felipe.proj.ecombackend.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepositorio userRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepositorio.findById(userId)
                .orElseThrow(() -> new ProcuraNaoEncontrada("User not found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return  Optional.of(request)
                .filter(user -> !userRepositorio.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setSenha(passwordEncoder.encode(request.getSenha()));
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return  userRepositorio.save(user);
                }) .orElseThrow(() -> new ExistenteException("Oops!" +request.getEmail() +" already exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return  userRepositorio.findById(userId).map(existingUser ->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepositorio.save(existingUser);
        }).orElseThrow(() -> new ProcuraNaoEncontrada("User not found!"));

    }

    @Override
    public void deleteUser(Long userId) {
        userRepositorio.findById(userId).ifPresentOrElse(userRepositorio :: delete, () ->{
            throw new ProcuraNaoEncontrada("User not found!");
        });
    }


    @Override
    public User getAuthenticatedUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepositorio.findByEmail(email);
    }
}
