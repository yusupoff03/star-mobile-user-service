package com.example.sofiyauserservice.domain.entity.user;

import com.example.sofiyauserservice.domain.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PermissionEntity> permissions;
}
