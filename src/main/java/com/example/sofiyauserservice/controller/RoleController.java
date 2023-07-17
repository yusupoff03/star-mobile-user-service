package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.RoleDto;
import com.example.sofiyauserservice.domain.entity.user.RoleEntity;
import com.example.sofiyauserservice.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole(ADMIN)")
    public ResponseEntity<RoleEntity> addRole(
            @RequestBody RoleDto roleDto
    ){
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @DeleteMapping("/{groupId}/delete")
    @PreAuthorize("hasRole(ADMIN)")
    public ResponseEntity deleteGroup(
            @PathVariable UUID groupId
    ){
        roleService.deleteById(groupId);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{groupId}update")
    @PreAuthorize("hasRole(ADMIN)")
    public ResponseEntity<RoleEntity> updateGroup(
            @PathVariable UUID roleId,
            @RequestBody RoleDto roleDto
    ){
        return ResponseEntity.ok(roleService.update(roleDto, roleId));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole(SUPER_ADMIN)")
    public ResponseEntity<List<RoleEntity>> getAll(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size
    ){
        return ResponseEntity.status(200).body(roleService.getAll(page,size));
    }
}
