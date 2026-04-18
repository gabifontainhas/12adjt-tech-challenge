package br.com.gabifontainhas.techchallenge.entity;

import br.com.gabifontainhas.techchallenge.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipcode;

    public Address(AddressDTO.Request dto) {
        this.street = dto.street();
        this.number = dto.number();
        this.city = dto.city();
        this.state = dto.state();
        this.zipcode = dto.zipcode();
    }

    public void update(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getNumber();
        this.state = address.getState();
        this.zipcode = address.getZipcode();
    }
}
