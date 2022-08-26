package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.entity.Client;
import com.gsmh.kz.property_for_sale.rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public void saveClient(Client client) {
    clientRepository.save(client);
  }

  @Override
  public Client getClient(int id) {
    Client client = null;
    Optional<Client> optionalClient = clientRepository.findById(id);
    if (optionalClient.isPresent()) {
      client = optionalClient.get();
    }
    return client;
  }

  @Override
  public void deleteClient(int id) {
    clientRepository.deleteById(id);
  }
}
