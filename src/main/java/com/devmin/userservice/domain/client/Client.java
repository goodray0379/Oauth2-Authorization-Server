package com.devmin.userservice.domain.client;

import com.devmin.userservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.UUID;

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

    @Builder
    public Client(Long id, String clientId, String clientSecret, String clientName, String companyName, String webUrl, String callbackUrl, String description) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.companyName = companyName;
        this.webUrl = webUrl;
        this.callbackUrl = callbackUrl;
        this.description = description;
    }
}
