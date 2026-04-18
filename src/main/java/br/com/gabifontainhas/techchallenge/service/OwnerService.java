package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Client;
import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void create(Owner owner) {
        this.ownerRepository.save(owner);
    }

    public void update(Long id, Owner updatedOwner) {
        var owner = ownerRepository.findClientById(id).get();
        owner.update(updatedOwner);
        this.ownerRepository.save(owner);
    }

    public List<Owner> findAll(int size, int offset) {
        return ownerRepository.findAll(size, offset);
    }
}
