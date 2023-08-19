package com.example.sofiyauserservice.service.role;

import com.example.sofiyauserservice.domain.dto.RoleDto;
import com.example.sofiyauserservice.domain.entity.user.RoleEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleEntity save(RoleDto roleDto);
    RoleEntity update(RoleDto roleDto, UUID id);
    void deleteById(UUID id);
    List<RoleEntity> getAll(int page, int size);

}
