package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.entity.Client;

import java.util.List;

public interface ClientService {

  List<Client> getAllClients();

  void saveClient (Client client);

  Client getClient(int id);

  void deleteClient(int id);
}
