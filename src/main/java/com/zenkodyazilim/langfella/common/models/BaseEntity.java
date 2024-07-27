package com.zenkodyazilim.langfella.common.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
