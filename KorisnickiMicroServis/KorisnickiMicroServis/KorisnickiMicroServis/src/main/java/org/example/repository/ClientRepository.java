package org.example.repository;

import org.example.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findClientByUsernameAndPassword(String username, String password);
    Optional<Client> findClientByEmailAndPassword(String email,String password);
    Optional<Client> findClientByEmail(String email);
}
