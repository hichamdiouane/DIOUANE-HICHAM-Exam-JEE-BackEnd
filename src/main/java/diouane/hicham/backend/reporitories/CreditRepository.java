package diouane.hicham.backend.reporitories;
import diouane.hicham.backend.entities.Credit;
import diouane.hicham.backend.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    long count();
    List<Credit> findByStatut(StatutCredit statut);
    List<Credit> findByClientId(Long clientId);
} 