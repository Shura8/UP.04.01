package com.example.autosale.model;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 20)
    private String name;
    @OneToMany
    private Set<UserModel> _users;
    public RoleModel(Long id, String name, Set<UserModel> users) {
        this.id = id;
        this.name = name;
        this._users = users;
    }
    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserModel> getUsers() {
        return _users;
    }

    public void setUsers(Set<UserModel> _users) {
        this._users = _users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
