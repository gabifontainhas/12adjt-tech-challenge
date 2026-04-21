package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Client;
import br.com.gabifontainhas.techchallenge.exception.EmailAlreadyExistsException;
import br.com.gabifontainhas.techchallenge.exception.UserNotFoundException;
import br.com.gabifontainhas.techchallenge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {
        if (clientRepository.existsUserByEmail(client.getEmail())) {
            throw new EmailAlreadyExistsException("E-mail already exists");
        } else {
            return this.clientRepository.save(client);
        }
    }

    public Client update(Long id, Client updatedClient) {
        var client = clientRepository.findClientById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        client.update(updatedClient);
        return this.clientRepository.save(client);
    }

    public List<Client> findAll(int size, int offset) {
        return clientRepository.findAll(size, offset);
    }

}
