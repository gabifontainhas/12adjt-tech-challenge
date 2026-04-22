package br.com.gabifontainhas.techchallenge.repository;

import br.com.gabifontainhas.techchallenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerById(Long id);

    @Query("SELECT c FROM Customer c LIMIT :size OFFSET :offset")
    List<Customer> findAll(int size, int offset);

    boolean existsUserByEmail(String email);
}
