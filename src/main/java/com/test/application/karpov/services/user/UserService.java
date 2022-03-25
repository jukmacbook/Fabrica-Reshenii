package com.test.application.karpov.services.user;

import com.test.application.karpov.services.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findByID(Long id);

    User save(User newUser);

    User update(User newUser, Long id);

    void delete(Long id);
}
