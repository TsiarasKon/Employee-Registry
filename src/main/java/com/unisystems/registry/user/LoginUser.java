package com.unisystems.registry.user;

import com.unisystems.registry.authority.Authority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "login_user")
public class LoginUser {

    @Id
    @Column(name = "username")
    private String username;

    private String password;
    private boolean enabled;
    //maybe one user has more than one authorities, this is the reason for OneToMany relation
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loginUser")
    private Set<Authority> authority = new HashSet<>();

    public LoginUser() {
    }

    public LoginUser(String username, String password, boolean enabled, Set<Authority> authority) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }
}
