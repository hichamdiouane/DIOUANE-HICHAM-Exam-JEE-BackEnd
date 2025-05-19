package diouane.hicham.backend.mappers;

import diouane.hicham.backend.dtos.*;
import diouane.hicham.backend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CreditMapper {
    
    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public CreditDTO fromCredit(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        BeanUtils.copyProperties(credit, creditDTO);
        creditDTO.setClientId(credit.getClient().getId());
        return creditDTO;
    }

    public Credit fromCreditDTO(CreditDTO creditDTO) {
        Credit credit = new Credit();
        BeanUtils.copyProperties(creditDTO, credit);
        return credit;
    }

    public CreditPersonnelDTO fromCreditPersonnel(CreditPersonnel creditPersonnel) {
        CreditPersonnelDTO creditPersonnelDTO = new CreditPersonnelDTO();
        BeanUtils.copyProperties(creditPersonnel, creditPersonnelDTO);
        creditPersonnelDTO.setClientId(creditPersonnel.getClient().getId());
        return creditPersonnelDTO;
    }

    public CreditPersonnel fromCreditPersonnelDTO(CreditPersonnelDTO creditPersonnelDTO) {
        CreditPersonnel creditPersonnel = new CreditPersonnel();
        BeanUtils.copyProperties(creditPersonnelDTO, creditPersonnel);
        return creditPersonnel;
    }

    public CreditImmobilierDTO fromCreditImmobilier(CreditImmobilier creditImmobilier) {
        CreditImmobilierDTO creditImmobilierDTO = new CreditImmobilierDTO();
        BeanUtils.copyProperties(creditImmobilier, creditImmobilierDTO);
        creditImmobilierDTO.setClientId(creditImmobilier.getClient().getId());
        return creditImmobilierDTO;
    }

    public CreditImmobilier fromCreditImmobilierDTO(CreditImmobilierDTO creditImmobilierDTO) {
        CreditImmobilier creditImmobilier = new CreditImmobilier();
        BeanUtils.copyProperties(creditImmobilierDTO, creditImmobilier);
        return creditImmobilier;
    }

    public CreditProfessionnelDTO fromCreditProfessionnel(CreditProfessionnel creditProfessionnel) {
        CreditProfessionnelDTO creditProfessionnelDTO = new CreditProfessionnelDTO();
        BeanUtils.copyProperties(creditProfessionnel, creditProfessionnelDTO);
        creditProfessionnelDTO.setClientId(creditProfessionnel.getClient().getId());
        return creditProfessionnelDTO;
    }

    public CreditProfessionnel fromCreditProfessionnelDTO(CreditProfessionnelDTO creditProfessionnelDTO) {
        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
        BeanUtils.copyProperties(creditProfessionnelDTO, creditProfessionnel);
        return creditProfessionnel;
    }

    public RemboursementDTO fromRemboursement(Remboursement remboursement) {
        RemboursementDTO remboursementDTO = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, remboursementDTO);
        remboursementDTO.setCreditId(remboursement.getCredit().getId());
        return remboursementDTO;
    }

    public Remboursement fromRemboursementDTO(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(remboursementDTO, remboursement);
        return remboursement;
    }
} 