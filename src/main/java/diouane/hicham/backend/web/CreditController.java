package diouane.hicham.backend.web;

import diouane.hicham.backend.dtos.*;
import diouane.hicham.backend.enums.StatutCredit;
import diouane.hicham.backend.exceptions.ClientNotFoundException;
import diouane.hicham.backend.exceptions.CreditNotFoundException;
import diouane.hicham.backend.services.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin("*")
@Slf4j
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/personnel")
    public ResponseEntity<CreditPersonnelDTO> createCreditPersonnel(@RequestBody CreditPersonnelDTO creditPersonnelDTO) throws ClientNotFoundException {
        log.info("Creating new personal credit");
        return ResponseEntity.ok(creditService.saveCreditPersonnel(creditPersonnelDTO));
    }

    @PostMapping("/immobilier")
    public ResponseEntity<CreditImmobilierDTO> createCreditImmobilier(@RequestBody CreditImmobilierDTO creditImmobilierDTO) throws ClientNotFoundException {
        log.info("Creating new real estate credit");
        return ResponseEntity.ok(creditService.saveCreditImmobilier(creditImmobilierDTO));
    }

    @PostMapping("/professionnel")
    public ResponseEntity<CreditProfessionnelDTO> createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditProfessionnelDTO) throws ClientNotFoundException {
        log.info("Creating new professional credit");
        return ResponseEntity.ok(creditService.saveCreditProfessionnel(creditProfessionnelDTO));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        log.info("Getting credits for client {}", clientId);
        return ResponseEntity.ok(creditService.getCreditsByClientId(clientId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatut(@PathVariable StatutCredit statut) {
        log.info("Getting credits with status {}", statut);
        return ResponseEntity.ok(creditService.getCreditsByStatut(statut));
    }

    @PutMapping("/{creditId}/statut")
    public ResponseEntity<CreditDTO> updateCreditStatut(
            @PathVariable Long creditId,
            @RequestParam StatutCredit newStatut) throws CreditNotFoundException {
        log.info("Updating credit {} status to {}", creditId, newStatut);
        return ResponseEntity.ok(creditService.updateCreditStatut(creditId, newStatut));
    }
} 