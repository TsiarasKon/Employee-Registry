package com.unisystems.registry.authority;

import com.unisystems.registry.user.User;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "authority")
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username",foreignKey=@ForeignKey(name="ix_auth_username"))
    private User user;

    public Authority() {
    }

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
