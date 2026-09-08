package com.kamalkavin96.filemanager.services.implimentation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserRes;
import com.kamalkavin96.filemanager.exception.UsernameExistException;
import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.repository.RoleRepo;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

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

        Set<Role> roles = new HashSet<>();
        String roleName = createUserReq.getRole() != null ? createUserReq.getRole() : "ROLE_USER";
        Role userRole = roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role " + roleName + " is not found in the database."));
        roles.add(userRole);

        user.setRoles(roles);

        User newUser = userRepo.save(user);

        return new UserRes(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                newUser.getEmail()

        );
    }

    @Override
    public List<UserRes> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> new UserRes(
                        user.getId(),
                        user.getUsername(),
                        user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                        user.getEmail()))
                .toList();
    }

}
