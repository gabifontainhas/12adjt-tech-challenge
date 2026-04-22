package br.com.gabifontainhas.techchallenge.entity;

import br.com.gabifontainhas.techchallenge.dto.CustomerDTO;
import br.com.gabifontainhas.techchallenge.dto.OwnerDTO;
import br.com.gabifontainhas.techchallenge.exception.InvalidPasswordException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    @Column(unique = true)
    private String login;
    private String password;

    private LocalDate lastUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public User(@Valid CustomerDTO.PostRequest dto) {
        this.email = dto.email();
        this.login = dto.email();
        this.name = dto.name();
        this.password = dto.password();
        this.address = new Address(dto.address());
        this.lastUpdate = LocalDate.now();
    }

    public User(@Valid CustomerDTO.PutRequest dto) {
        this.name = dto.name();
        this.address = new Address(dto.address());
    }

    public User(@Valid OwnerDTO.PostRequest dto) {
        this.email = dto.email();
        this.login = dto.email();
        this.name = dto.name();
        this.password = dto.password();
        this.address = new Address(dto.address());
        this.lastUpdate = LocalDate.now();
    }

    public User(@Valid OwnerDTO.PutRequest dto) {
        this.name = dto.name();
        this.address = new Address(dto.address());
        this.lastUpdate = LocalDate.now();
    }

    public void update(User user) {
        this.name = user.getName();
        this.address.update(user.getAddress());
    }

    public void updatePassword (String newPassword, String oldPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        } else {
            throw new InvalidPasswordException("The current password provided is incorrect");
        }
    }
}
