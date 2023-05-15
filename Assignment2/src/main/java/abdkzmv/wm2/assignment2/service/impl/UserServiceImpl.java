package abdkzmv.wm2.assignment2.service.impl;

import abdkzmv.wm2.assignment2.model.entity.User;
import abdkzmv.wm2.assignment2.repository.MovieRepository;
import abdkzmv.wm2.assignment2.repository.UserRepository;
import abdkzmv.wm2.assignment2.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private final MovieRepository movieRepository;

    public UserServiceImpl(UserRepository userRepo,
                           MovieRepository movieRepository) {
        this.userRepo = userRepo;
        this.movieRepository = movieRepository;
    }


    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void add(User user) {
        userRepo.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void update(Long id, User user) {
        userRepo.deleteById(id);
        user.setId(id);
        userRepo.save(user);
    }

    @Override
    public void saveRoleToUser(String roles, Long userId) {
        userRepo.saveToUser(roles, userId);
    }



}
