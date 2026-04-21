package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.exception.EmailAlreadyExistsException;
import br.com.gabifontainhas.techchallenge.exception.UserNotFoundException;
import br.com.gabifontainhas.techchallenge.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner create(Owner owner) {
        if (ownerRepository.existsUserByEmail(owner.getEmail())) {
            throw new EmailAlreadyExistsException("E-mail already exists");
        } else {
            return this.ownerRepository.save(owner);
        }
    }

    public Owner update(Long id, Owner updatedOwner) {
        var owner = ownerRepository.findOwnerById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        owner.update(updatedOwner);
        return this.ownerRepository.save(owner);
    }


    public List<Owner> findAll(int size, int offset) {
        return ownerRepository.findAll(size, offset);
    }
}
