package com.kamalkavin96.filemanager.services.implimentation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.CreateUserRes;
import com.kamalkavin96.filemanager.exception.UsernameExistException;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public CreateUserRes createUser(CreateUserReq createUserReq) {

        if(userRepo.existsByUsername(createUserReq.getUsername())){
            throw new UsernameExistException("Username already exist");
        }

        User user = new User();
        user.setUsername(createUserReq.getUsername());
        user.setPassword(createUserReq.getPassword());
        user.setRole(createUserReq.getRole());

        User newUser = userRepo.save(user);

        return new CreateUserRes(
            newUser.getId(),
            newUser.getUsername(),
            newUser.getPassword(),
            newUser.getEmail()
        );
    }

    @Override
    public List<CreateUserRes> getAllUsers() {
        return userRepo.findAll()
            .stream()
            .map(user -> new CreateUserRes(
                user.getId(), 
                user.getUsername(), 
                user.getRole(), 
                user.getEmail())
            )
            .toList();
    }

}
