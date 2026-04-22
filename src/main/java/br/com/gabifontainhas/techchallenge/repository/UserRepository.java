package br.com.gabifontainhas.techchallenge.repository;

import br.com.gabifontainhas.techchallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id); // como trazer o address?

    List<User> findUserByName(String name);

    boolean existsUserByLoginAndPassword(String login, String password);

    @Query("SELECT u FROM User u LIMIT :size OFFSET :offset")
    List<User> findAllUsers(int size, int offset);

    boolean existsUserByEmail(String email);

}
