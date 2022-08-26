package com.gsmh.kz.property_for_sale.rest.controller;

import com.gsmh.kz.property_for_sale.rest.entity.Ad;
import com.gsmh.kz.property_for_sale.rest.entity.Client;
import com.gsmh.kz.property_for_sale.rest.service.AdService;
import com.gsmh.kz.property_for_sale.rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

  private final ClientService clientService;
  private final AdService adService;


  public RESTController(ClientService clientService, AdService adService) {
    this.clientService = clientService;
    this.adService = adService;
  }

  @GetMapping("/clients")
  public List<Client> shawAllClients() {
    List<Client> allClients = clientService.getAllClients();
    return allClients;
  }

  @GetMapping("/clients/{id}")
  public Client getClient(@PathVariable int id) {
    Client client = clientService.getClient(id);
    return client;
  }

  @PostMapping("/clients")
  public Client addNewClient(@RequestBody Client client) {
    clientService.saveClient(client);
    return client;
  }

  @PutMapping("clients")
  public Client updateClients(@RequestBody Client client) {
    clientService.saveClient(client);
    return client;
  }

  @DeleteMapping("/clients/{id}")
  public String deleteEmployee(@PathVariable int id) {
    clientService.deleteClient(id);
    return "Client with id " + id + " was deleted";
  }

  @GetMapping("/ads")
  public List<Ad> shawAllAds() {
    List<Ad> allAds = adService.getAllAds();
    return allAds;
  }

  @GetMapping("ads/{id}")
  public Ad getAd(@PathVariable int id) {
    Ad ad = adService.getAd(id);
    return ad;
  }

  @PostMapping("/ads")
  public Ad addNewAd(@RequestBody Ad ad) {
    adService.saveAd(ad);
    return ad;
  }

}
