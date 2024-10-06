package org.example.repository;

import org.example.domain.Client;
import org.example.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
    Optional<Manager> findManagerByEmailAndPassword(String email,String password);
    Optional<Manager> findManagerByEmail(String email);


}