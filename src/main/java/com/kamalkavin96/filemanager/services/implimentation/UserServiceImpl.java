package com.kamalkavin96.filemanager.services.implimentation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserRes;
import com.kamalkavin96.filemanager.exception.UsernameExistException;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.repository.RoleRepo;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserRes createUser(CreateUserReq createUserReq) {

        if (userRepo.existsByUsername(createUserReq.getUsername())) {
            throw new UsernameExistException("Username already exist");
        }

        User user = new User();
        user.setUsername(createUserReq.getUsername());
        user.setPassword(createUserReq.getPassword());
        user.setEmail(createUserReq.getEmail());
        user.setDob(createUserReq.getDob());

        User newUser = userRepo.save(user);

        return new UserRes(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail());
    }

    @Override
    public List<UserRes> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> new UserRes(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()))
                .toList();
    }

}
