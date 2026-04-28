package br.com.gabifontainhas.techchallenge.entity;

import br.com.gabifontainhas.techchallenge.dto.CustomerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Customer extends User {

    private String phoneNumber;

    public Customer(
            @Valid
            CustomerDTO.PostRequest dto) {
        super(dto);
        this.phoneNumber = dto.phoneNumber();
    }

    public Customer(
            @Valid
            CustomerDTO.PutRequest dto) {
        super(dto);
        this.phoneNumber = dto.phoneNumber();
    }

    public void update(Customer customer) {
        super.update(customer);
        this.phoneNumber = customer.getPhoneNumber();
    }
}
