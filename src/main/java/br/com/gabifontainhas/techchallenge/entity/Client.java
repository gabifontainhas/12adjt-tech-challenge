package br.com.gabifontainhas.techchallenge.entity;

import br.com.gabifontainhas.techchallenge.dto.ClientDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Client extends User {

    private String phoneNumber;

    public Client(
            @Valid
            ClientDTO.PostRequest dto) {
        super(dto);
        this.phoneNumber = dto.phoneNumber();
    }

    public Client(
            @Valid
            ClientDTO.PutRequest dto) {
        super(dto);
        this.phoneNumber = dto.phoneNumber();
    }

    public void update(Client updatedClient) {
        super.update(updatedClient);
        this.phoneNumber = updatedClient.getPhoneNumber();
    }
}
