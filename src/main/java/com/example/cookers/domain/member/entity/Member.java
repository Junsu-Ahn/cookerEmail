package com.example.cookers.domain.member.entity;

import com.example.cookers.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Comment("유저 아이디")
    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true)
    private String nickname;
    private String email;
    private String providerTypeCode;
    private String profile_url;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long hit;
}
