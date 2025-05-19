package diouane.hicham.backend.reporitories;
import diouane.hicham.backend.entities.Remboursement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCreditId(Long creditId);
    Page<Remboursement> findByCreditId(Long creditId, Pageable pageable);
}