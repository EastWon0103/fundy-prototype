package com.example.persistent.user.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name = "FUNDY_USER")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICKNAME",unique = true, nullable = false)
    private String nickname;

    @Column(name = "ROLE", nullable = false)
    private int role;

    @Column(name = "PROFILE", nullable = false)
    private String profile;

    @OneToMany(mappedBy = "owner")
    List<AccountModel> accounts = new ArrayList<>();
}
