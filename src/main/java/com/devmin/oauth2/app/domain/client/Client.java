package com.devmin.oauth2.app.domain.client;

import com.devmin.oauth2.app.domain.BaseTimeEntity;
import com.devmin.oauth2.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class Client extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique=true)
    private String clientId;

    @Column(length = 100, nullable = false)
    private String clientSecret;

    @Column(length = 20, nullable = false)
    private String clientName;

    @Column(length = 40, nullable = false)
    private String companyName;

    @Column(length = 100, nullable = false)
    private String webUrl;

    @Column(length = 100, nullable = false)
    private String callbackUrl;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Builder
    public Client(Long id, String clientId, String clientSecret, String clientName, String companyName, String webUrl, String callbackUrl, String description, User user) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.companyName = companyName;
        this.webUrl = webUrl;
        this.callbackUrl = callbackUrl;
        this.description = description;
        this.user = user;
    }
}
