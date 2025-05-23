package diouane.hicham.backend.reporitories;

import diouane.hicham.backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContains(String keyword);
    Client findByEmail(String email);
    long count();
} 