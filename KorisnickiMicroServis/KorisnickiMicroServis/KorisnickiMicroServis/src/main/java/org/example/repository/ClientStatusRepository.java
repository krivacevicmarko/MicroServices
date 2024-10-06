package org.example.repository;

import org.example.domain.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStatusRepository extends JpaRepository<ClientStatus, Integer> {
}
