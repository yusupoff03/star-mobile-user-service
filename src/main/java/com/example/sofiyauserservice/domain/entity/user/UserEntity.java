package com.example.sofiyauserservice.domain.entity.user;

import com.example.sofiyauserservice.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {
   private String name;
   @Column(unique = true,nullable = false)
   private String email;
   @Column(nullable = false)
   private String password;
    @ManyToMany
    private List<RoleEntity> roles;

    @ManyToMany
    private List<PermissionEntity> permissions;
    private UserState state;
}
