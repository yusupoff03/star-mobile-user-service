package com.example.sofiyauserservice.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.db.myiuser.entity.BaseEntity;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "roles")
public class RoleEntity extends BaseEntity {
    private String name;
    @ManyToMany
    private List<PermissionEntity> permissionEntityList;
}
