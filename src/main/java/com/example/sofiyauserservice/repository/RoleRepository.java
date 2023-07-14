package com.example.sofiyauserservice.repository;

import com.example.sofiyauserservice.domain.entity.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roles);
}
