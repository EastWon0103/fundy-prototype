package com.example.fundydomain.domain.user;

import com.example.fundydomain.consists.enums.FundyRole;
import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.user.converter.RoleAttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class FundyUser {
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

    @Convert(converter = RoleAttributeConverter.class)
    @Column(name = "ROLE", nullable = false)
    private FundyRole role;

    @Column(name = "PROFILE", nullable = false)
    private String profile;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts = new ArrayList<>();

    @Builder
    private FundyUser(String email, String password, String nickname, FundyRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
