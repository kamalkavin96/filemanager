package com.kamalkavin96.filemanager.services.implimentation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.request.CreateRoleReq;
import com.kamalkavin96.filemanager.dto.response.RoleRes;
import com.kamalkavin96.filemanager.exception.RoleNotFoundException;
import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.repository.RoleRepo;
import com.kamalkavin96.filemanager.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImplc implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public RoleRes create(CreateRoleReq roleReq) {

        Role role = new Role();
        role.setName(roleReq.getName());
        role.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));

        Role newRole = roleRepo.save(role);
        return new RoleRes(newRole.getId(), newRole.getName());
    }

    @Override
    public List<RoleRes> getAll() {
        return roleRepo.findAll()
            .stream()
            .map(role -> new RoleRes(
                role.getId(), 
                role.getName())
            )
            .toList();
    }

    @Override
    public Map<String, Object> delete(Long id) {
        
        Role role = roleRepo.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found for Id: %d".formatted(id)));
        roleRepo.deleteById(role.getId());
        return  Map.of("deleted", true, "message","Role: %s deleted succesfully".formatted(role.getName()));
    }

}
