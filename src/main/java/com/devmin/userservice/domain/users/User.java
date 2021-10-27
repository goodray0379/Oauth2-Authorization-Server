package com.devmin.userservice.domain.users;

import com.devmin.userservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @ColumnDefault("user")
    @Column(nullable = false)
    private String role;

    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @Builder
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
