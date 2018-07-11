package com.ooa1769.bs.member.domain;

import com.ooa1769.bs.support.domain.AbstractEntity;
import com.ooa1769.bs.support.domain.BooleanToYNConverter;
import lombok.Getter;

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
    @Getter
    private String email;

    private String name;

    @Getter
    private String password;

    @Getter
    private boolean enabled;

}
