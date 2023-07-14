package com.example.sofiyauserservice.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.db.myiuser.entity.BaseEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "permissions")
public class PermissionEntity extends BaseEntity {
    private String name;
    @ManyToMany
    private List<RoleEntity> roleEntityList;
}
