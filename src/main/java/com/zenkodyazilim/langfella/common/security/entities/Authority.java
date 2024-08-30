package com.zenkodyazilim.langfella.common.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "AUTHORITIES")
@Getter
@Setter
public class Authority {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private CustomUser user;

    private String authority;
}
