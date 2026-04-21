package br.com.gabifontainhas.techchallenge.repository;

import br.com.gabifontainhas.techchallenge.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientById(Long id);

    @Query("SELECT c FROM Client c LIMIT :size OFFSET :offset")
    List<Client> findAll(int size, int offset);

    boolean existsUserByEmail(String email);
}
