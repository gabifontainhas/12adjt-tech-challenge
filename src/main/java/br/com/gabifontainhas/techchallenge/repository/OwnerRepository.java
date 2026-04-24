package br.com.gabifontainhas.techchallenge.repository;

import br.com.gabifontainhas.techchallenge.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findOwnerById(Long id);

    @Query("SELECT o FROM Owner o LIMIT :size OFFSET :offset")
    List<Owner> findAll(int size, int offset);

    List<Owner> findOwnerByName(String name);
}
