package com.test.application.karpov.services.user;

import com.test.application.karpov.exceptions.UserNotFoundException;
import com.test.application.karpov.repositories.user.UserRepository;
import com.test.application.karpov.services.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByID(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setQuizzes(newUser.getQuizzes());
                    return save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return save(newUser);
                });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
