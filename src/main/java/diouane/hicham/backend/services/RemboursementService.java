package  diouane.hicham.backend.services;

import  diouane.hicham.backend.dtos.RemboursementDTO;
import  diouane.hicham.backend.entities.Credit;
import  diouane.hicham.backend.entities.Remboursement;
import  diouane.hicham.backend.reporitories.CreditRepository;
import  diouane.hicham.backend.reporitories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemboursementService {
    @Autowired
    private RemboursementRepository remboursementRepository;
    
    @Autowired
    private CreditRepository creditRepository;

    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        
        Remboursement remboursement = new Remboursement();
        remboursement.setDate(LocalDate.now());
        remboursement.setMontant(remboursementDTO.getMontant());
        remboursement.setType(remboursementDTO.getType());
        remboursement.setCredit(credit);
        
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return convertToDTO(savedRemboursement);
    }

    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RemboursementDTO convertToDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit().getId());
        return dto;
    }
} 