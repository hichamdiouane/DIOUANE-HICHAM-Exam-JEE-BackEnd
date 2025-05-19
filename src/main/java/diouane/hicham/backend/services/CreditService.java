package  diouane.hicham.backend.services;

import  diouane.hicham.backend.dtos.*;
import  diouane.hicham.backend.enums.StatutCredit;
import  diouane.hicham.backend.exceptions.ClientNotFoundException;
import  diouane.hicham.backend.exceptions.CreditNotFoundException;

import java.util.List;

public interface CreditService {
    CreditDTO saveCredit(CreditDTO creditDTO) throws ClientNotFoundException;
    CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditPersonnelDTO) throws ClientNotFoundException;
    CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditImmobilierDTO) throws ClientNotFoundException;
    CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditProfessionnelDTO) throws ClientNotFoundException;
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getCreditsByStatut(StatutCredit statut);
    CreditDTO updateCreditStatut(Long creditId, StatutCredit newStatut) throws CreditNotFoundException;
} 