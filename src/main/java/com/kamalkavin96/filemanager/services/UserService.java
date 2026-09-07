package com.kamalkavin96.filemanager.services;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.CreateUserRes;

public interface UserService {

    CreateUserRes createUser(CreateUserReq createUserReq);
    
}
