//package com.devmin.oauth2.app.domain.client.clientUser;
//
//import com.devmin.oauth2.app.domain.BaseTimeEntity;
//import com.devmin.oauth2.app.domain.client.Client;
//import com.devmin.oauth2.app.domain.user.User;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class ClientUser extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Client client;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private User user;
//
//    @Column(length = 2000, nullable = false)
//    private String authorizationCode;
//
//    @Builder
//    public ClientUser(Long id, Client client, User user, String authorizationCode) {
//        this.id = id;
//        this.client = client;
//        this.user = user;
//        this.authorizationCode = authorizationCode;
//    }
//}
