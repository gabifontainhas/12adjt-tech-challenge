package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Client;
import br.com.gabifontainhas.techchallenge.entity.User;
import br.com.gabifontainhas.techchallenge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void create(Client client) {
            this.clientRepository.save(client);
    }

    public void update(Long id, Client updatedClient) {
        var client = clientRepository.findClientById(id).get();
        client.update(updatedClient);
        this.clientRepository.save(client);
    }

    public List<Client> findAll(int size, int offset) {
        return clientRepository.findAll(size, offset);
    }

}
