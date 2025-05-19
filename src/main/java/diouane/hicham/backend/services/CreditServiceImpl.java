package diouane.hicham.backend.services;

import diouane.hicham.backend.dtos.*;
import diouane.hicham.backend.entities.*;
import diouane.hicham.backend.enums.StatutCredit;
import diouane.hicham.backend.exceptions.ClientNotFoundException;
import diouane.hicham.backend.exceptions.CreditNotFoundException;
import diouane.hicham.backend.mappers.CreditMapper;
import diouane.hicham.backend.reporitories.ClientRepository;
import diouane.hicham.backend.reporitories.CreditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreditMapper creditMapper;

    @Override
    public CreditDTO saveCredit(CreditDTO creditDTO) throws ClientNotFoundException {
        log.info("Saving new credit");
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        
        Credit credit = creditMapper.fromCreditDTO(creditDTO);
        credit.setDateDemande(LocalDate.now());
        credit.setStatut(StatutCredit.EN_COURS);
        credit.setClient(client);
        
        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.fromCredit(savedCredit);
    }

    @Override
    public CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditPersonnelDTO) throws ClientNotFoundException {
        log.info("Saving new personal credit");
        Client client = clientRepository.findById(creditPersonnelDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        
        CreditPersonnel creditPersonnel = creditMapper.fromCreditPersonnelDTO(creditPersonnelDTO);
        creditPersonnel.setDateDemande(LocalDate.now());
        creditPersonnel.setStatut(StatutCredit.EN_COURS);
        creditPersonnel.setClient(client);
        
        CreditPersonnel savedCredit = creditRepository.save(creditPersonnel);
        return creditMapper.fromCreditPersonnel(savedCredit);
    }

    @Override
    public CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditImmobilierDTO) throws ClientNotFoundException {
        log.info("Saving new real estate credit");
        Client client = clientRepository.findById(creditImmobilierDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        
        CreditImmobilier creditImmobilier = creditMapper.fromCreditImmobilierDTO(creditImmobilierDTO);
        creditImmobilier.setDateDemande(LocalDate.now());
        creditImmobilier.setStatut(StatutCredit.EN_COURS);
        creditImmobilier.setClient(client);
        
        CreditImmobilier savedCredit = creditRepository.save(creditImmobilier);
        return creditMapper.fromCreditImmobilier(savedCredit);
    }

    @Override
    public CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditProfessionnelDTO) throws ClientNotFoundException {
        log.info("Saving new professional credit");
        Client client = clientRepository.findById(creditProfessionnelDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        
        CreditProfessionnel creditProfessionnel = creditMapper.fromCreditProfessionnelDTO(creditProfessionnelDTO);
        creditProfessionnel.setDateDemande(LocalDate.now());
        creditProfessionnel.setStatut(StatutCredit.EN_COURS);
        creditProfessionnel.setClient(client);
        
        CreditProfessionnel savedCredit = creditRepository.save(creditProfessionnel);
        return creditMapper.fromCreditProfessionnel(savedCredit);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        log.info("Getting credits for client {}", clientId);
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatut(StatutCredit statut) {
        log.info("Getting credits with status {}", statut);
        return creditRepository.findByStatut(statut).stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCreditStatut(Long creditId, StatutCredit newStatut) throws CreditNotFoundException {
        log.info("Updating credit {} status to {}", creditId, newStatut);
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found"));
        
        credit.setStatut(newStatut);
        if (newStatut == StatutCredit.ACCEPTE) {
            credit.setDateAcceptation(LocalDate.now());
        }
        
        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.fromCredit(updatedCredit);
    }
} 