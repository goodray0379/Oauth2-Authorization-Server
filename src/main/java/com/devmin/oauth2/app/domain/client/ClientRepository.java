package com.devmin.oauth2.app.domain.client;

import com.devmin.oauth2.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByUser(User user);
}
