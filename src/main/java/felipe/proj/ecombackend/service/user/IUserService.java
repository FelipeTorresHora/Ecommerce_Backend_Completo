package felipe.proj.ecombackend.service.user;

import felipe.proj.ecombackend.model.User;
import felipe.proj.ecombackend.request.CreateUserRequest;
import felipe.proj.ecombackend.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    User getAuthenticatedUser();
}
