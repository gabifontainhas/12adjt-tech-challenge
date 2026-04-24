package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.exception.EmailAlreadyExistsException;
import br.com.gabifontainhas.techchallenge.exception.UserNotFoundException;
import br.com.gabifontainhas.techchallenge.repository.OwnerRepository;
import br.com.gabifontainhas.techchallenge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;


    public OwnerService(OwnerRepository ownerRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    public List<Owner> findAll(int size, int offset) {
        return ownerRepository.findAll(size, offset);
    }

    public Owner findOwnerById(Long id) {
        return ownerRepository.findOwnerById(id).orElseThrow(() -> new UserNotFoundException("Owner not found"));
    }

    public List<Owner> findOwnerByName(String name) {
        var ownerList = ownerRepository.findOwnerByName(name);
        if (ownerList.isEmpty()) {
            throw new UserNotFoundException("Owner not found");
        } else {
            return ownerList;
        }
    }

    public Owner create(Owner owner) {
        if (userRepository.existsUserByEmail(owner.getEmail())) {
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

}
