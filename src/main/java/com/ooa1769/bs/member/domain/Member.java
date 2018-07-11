package com.ooa1769.bs.member.domain;

import com.ooa1769.bs.support.domain.AbstractEntity;
import com.ooa1769.bs.support.domain.BooleanToYNConverter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Convert(converter = BooleanToYNConverter.class, attributeName = "enabled")
public class Member extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    private String name;

    private String password;

    private boolean enabled;

}
