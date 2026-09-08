package com.kamalkavin96.filemanager.services;

import java.util.List;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserRes;

public interface UserService {

    UserRes createUser(CreateUserReq createUserReq);
    List<UserRes> getAllUsers();
    
}
