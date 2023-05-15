package abdkzmv.wm2.assignment2.service.inter;

import abdkzmv.wm2.assignment2.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getById(Long id);
    void add(User user);
    void deleteById(Long id);
    void update(Long id, User user);

    void saveRoleToUser(String roles, Long userId);
}