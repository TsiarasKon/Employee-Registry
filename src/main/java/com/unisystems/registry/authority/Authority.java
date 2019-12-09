package com.unisystems.registry.authority;

import com.unisystems.registry.user.LoginUser;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username",foreignKey=@ForeignKey(name="ix_auth_username"))
    private LoginUser loginUser;

    public Authority() {
    }

    public Authority(String role, LoginUser loginUser) {
        this.role = role;
        this.loginUser = loginUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
}
