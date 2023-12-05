package com.example.autosale.model;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    @NotBlank(message = "FIO is required")
    private String fio;
    @Min(message = "Age should be more than 0", value = 0)
    private int age;
    @NotBlank(message = "Username is required")
    @Size(min = 5)
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 5)
    private String password;
    @ManyToOne
    private RoleModel _role;

    public UserModel() {}
    public UserModel(Long id, String _fio, int _age, String _username, String _password,  RoleModel role){
        _id = id;
        fio = _fio;
        age = _age;
        username = _username;
        password = _password;
        _role = role;
    }
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String _fio) {
        this.fio = _fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int _age) {
        this.age = _age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public RoleModel getRole() {
        return _role;
    }

    public void setRole(RoleModel _role) {
        this._role = _role;
    }
    @Override
    public String getAuthority() {
        return getFio();
    }

}
