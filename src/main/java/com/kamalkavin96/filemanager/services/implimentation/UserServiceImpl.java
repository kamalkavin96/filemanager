package com.kamalkavin96.filemanager.services.implimentation;

import com.kamalkavin96.filemanager.repository.UserRolesRepo;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dao.UserDetailDao;
import com.kamalkavin96.filemanager.dto.request.user.CreateUserReq;
import com.kamalkavin96.filemanager.dto.request.user.UpdateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserDetailRes;
import com.kamalkavin96.filemanager.exception.UserNotFoundException;
import com.kamalkavin96.filemanager.exception.UsernameExistException;
import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.repository.RoleRepo;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.services.UserService;

import io.jsonwebtoken.lang.Arrays;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRolesRepo userRolesRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserRolesServiceImpl userRolesServiceImpl;

    @Override
    public UserDetailRes createUser(CreateUserReq createUserReq) {

        if (userRepo.existsByUsername(createUserReq.getUsername())) {
            throw new UsernameExistException("Username already exist");
        }

        User user = new User();
        user.setUsername(createUserReq.getUsername());
        user.setPassword(createUserReq.getPassword());
        user.setEmail(createUserReq.getEmail());
        user.setDob(createUserReq.getDob());

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        user.setCreatedAt(localDateTime);
        user.setUpdateAt(localDateTime);

        User newUser = userRepo.save(user);
        Role role = roleRepo.findByName("ROLE_USER").orElse(null);
        if (role != null) {
            userRolesServiceImpl.addUserRole(newUser.getId(), role.getId());
        }

        List<Role> roles = userRolesServiceImpl.getUserRole(newUser.getId());

        return new UserDetailRes(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getDob(),
                newUser.getCreatedAt(),
                newUser.getUpdateAt(),
                roles.stream().map(Role::getName).toList());
    }

    @Override
    public List<UserDetailRes> getAllUsers() {
        return  userRolesRepo.getAllUsersDetails()
            .stream()
            .map(u->new UserDetailRes(
                u.getId(), 
                u.getUsername(), 
                u.getEmail(), 
                u.getDob(), 
                u.getCreatedAt(), 
                u.getUpdateAt(), 
                Arrays.asList(u.getRoles().split(",")))
            ).toList();
    }

    @Override
    public UserDetailRes getUser(Long userId) {

        UserDetailDao user = userRolesRepo.getUsersDetails(userId);

        return new UserDetailRes(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDob(),
                user.getCreatedAt(),
                user.getUpdateAt(),
                Arrays.asList(user.getRoles().split(",")));
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {

        User user = findUserById(userId);
        userRolesRepo.deleteByUserId(userId);
        userRepo.deleteById(user.getId());
        return true;
    }

    @Override
    public void updateUser(UpdateUserReq updateUserReq) {
        User user = findUserById(updateUserReq.getId());

        if (!updateUserReq.getUserName().isEmpty() || updateUserReq.getUserName() != null) {
            user.setUsername(updateUserReq.getUserName());
        } else if (!updateUserReq.getEmail().isEmpty() || updateUserReq.getEmail() != null) {
            user.setEmail(updateUserReq.getEmail());
        }
    }

    private User findUserById(Long userId) {
        return userRepo
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for id: %s".formatted(userId)));

    }

}
