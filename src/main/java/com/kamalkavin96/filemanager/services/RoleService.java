package com.kamalkavin96.filemanager.services;

import java.util.List;
import java.util.Map;

import com.kamalkavin96.filemanager.dto.request.CreateRoleReq;
import com.kamalkavin96.filemanager.dto.response.RoleRes;

public interface RoleService {
    RoleRes create(CreateRoleReq roleReq);
    List<RoleRes> getAll();
    Map<String, Object> delete(Long id);
}
