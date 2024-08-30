package com.zenkodyazilim.langfella.common.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private Boolean enabled;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Authority> authorities;
}
