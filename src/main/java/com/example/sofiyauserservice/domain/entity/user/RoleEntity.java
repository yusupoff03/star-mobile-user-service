package com.example.sofiyauserservice.domain.entity.user;

import com.example.sofiyauserservice.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity extends BaseEntity {
    private String name;
    @ManyToMany
    private List<PermissionEntity> permissions;
}
