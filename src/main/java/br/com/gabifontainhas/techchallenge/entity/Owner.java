package br.com.gabifontainhas.techchallenge.entity;

import br.com.gabifontainhas.techchallenge.dto.OwnerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Owner extends User{

    private String restaurantName;

    public Owner(OwnerDTO.PostRequest dto) {
        super(dto);
        this.restaurantName = dto.restaurantName();
    }

    public Owner(OwnerDTO.PutRequest dto) {
        super(dto);
        this.restaurantName = dto.restaurantName();
    }

    public void update(Owner owner) {
        super.update(owner);
        this.restaurantName = owner.getRestaurantName();
    }

}
