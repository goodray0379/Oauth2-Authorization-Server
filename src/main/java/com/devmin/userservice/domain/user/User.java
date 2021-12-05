package com.devmin.userservice.domain.user;

import com.devmin.userservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class User<refreshToken> extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false, unique=true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @ColumnDefault("'USER'")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(length = 100)
    private String refreshToken;

    @Builder
    public User(Long id, String username, String password, Role role, String refreshToken) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
