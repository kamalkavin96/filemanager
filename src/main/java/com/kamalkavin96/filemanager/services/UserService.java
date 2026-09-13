package com.kamalkavin96.filemanager.services;

import java.util.List;

import com.kamalkavin96.filemanager.dto.request.user.CreateUserReq;
import com.kamalkavin96.filemanager.dto.request.user.UpdateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserDetailRes;

public interface UserService {

    UserDetailRes createUser(CreateUserReq createUserReq);
    List<UserDetailRes> getAllUsers();
    UserDetailRes getUser(Long userId);
    boolean deleteUser(Long userId);
    void updateUser(UpdateUserReq updateUserReq);
    UserDetailRes getCurrentUser(String email);
}
