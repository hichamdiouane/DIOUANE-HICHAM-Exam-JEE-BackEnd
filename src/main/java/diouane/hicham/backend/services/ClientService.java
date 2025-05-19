package diouane.hicham.backend.services;

import  diouane.hicham.backend.dtos.ClientDTO;
import  diouane.hicham.backend.entities.Client;
import  diouane.hicham.backend.reporitories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ClientDTO getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return client != null ? convertToDTO(client) : null;
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail());
    }
} 