package diouane.hicham.backend;

import diouane.hicham.backend.entities.Client;
import diouane.hicham.backend.entities.CreditPersonnel;
import diouane.hicham.backend.entities.CreditImmobilier;
import diouane.hicham.backend.entities.CreditProfessionnel;
import diouane.hicham.backend.entities.Remboursement;
import diouane.hicham.backend.enums.StatutCredit;
import diouane.hicham.backend.enums.TypeBien;
import diouane.hicham.backend.enums.TypeRemboursement;
import diouane.hicham.backend.reporitories.ClientRepository;
import diouane.hicham.backend.reporitories.CreditRepository;
import diouane.hicham.backend.reporitories.RemboursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {
    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {
            try {
                logger.info("Starting data initialization...");

                // Création des clients
                Stream.of("Hassan", "Mohammed", "Fatima").forEach(name -> {
                    try {
                        Client client = new Client();
                        client.setNom(name);
                        client.setEmail(name.toLowerCase() + "@gmail.com");
                        clientRepository.save(client);
                        logger.info("Client created: {}", name);
                    } catch (Exception e) {
                        logger.error("Error creating client {}: {}", name, e.getMessage());
                    }
                });

                // Création des crédits pour chaque client
                clientRepository.findAll().forEach(client -> {
                    try {
                        // Crédit Personnel
                        CreditPersonnel creditPersonnel = new CreditPersonnel();
                        creditPersonnel.setDateDemande(LocalDate.now());
                        creditPersonnel.setStatut(StatutCredit.EN_COURS);
                        creditPersonnel.setMontant(Math.random() * 100000);
                        creditPersonnel.setDureeRemboursement(12 + (int)(Math.random() * 48));
                        creditPersonnel.setTauxInteret(3.0 + Math.random() * 2.0);
                        creditPersonnel.setClient(client);
                        creditPersonnel.setMotif("Achat de voiture");
                        creditRepository.save(creditPersonnel);
                        logger.info("Personal credit created for client: {}", client.getNom());

                        // Crédit Immobilier
                        CreditImmobilier creditImmobilier = new CreditImmobilier();
                        creditImmobilier.setDateDemande(LocalDate.now());
                        creditImmobilier.setStatut(StatutCredit.ACCEPTE);
                        creditImmobilier.setDateAcceptation(LocalDate.now());
                        creditImmobilier.setMontant(500000);
                        creditImmobilier.setDureeRemboursement(240);
                        creditImmobilier.setTauxInteret(3.5);
                        creditImmobilier.setClient(client);
                        creditImmobilier.setTypeBien(TypeBien.APPARTEMENT);
                        creditRepository.save(creditImmobilier);
                        logger.info("Real estate credit created for client: {}", client.getNom());

                        // Crédit Professionnel
                        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
                        creditProfessionnel.setDateDemande(LocalDate.now());
                        creditProfessionnel.setStatut(StatutCredit.EN_COURS);
                        creditProfessionnel.setMontant(200000);
                        creditProfessionnel.setDureeRemboursement(60);
                        creditProfessionnel.setTauxInteret(5.0);
                        creditProfessionnel.setClient(client);
                        creditProfessionnel.setMotif("Développement d'activité");
                        creditProfessionnel.setRaisonSociale("Entreprise " + client.getNom());
                        creditRepository.save(creditProfessionnel);
                        logger.info("Professional credit created for client: {}", client.getNom());
                    } catch (Exception e) {
                        logger.error("Error creating credits for client {}: {}", client.getNom(), e.getMessage());
                    }
                });

                // Création des remboursements pour les crédits acceptés
                creditRepository.findByStatut(StatutCredit.ACCEPTE).forEach(credit -> {
                    try {
                        if (credit.getDureeRemboursement() <= 0) {
                            logger.error("Invalid repayment duration for credit: {}", credit.getId());
                            return;
                        }

                        // Création de 3 remboursements mensuels
                        for (int i = 0; i < 3; i++) {
                            Remboursement remboursement = new Remboursement();
                            remboursement.setDate(LocalDate.now().plusMonths(i));
                            remboursement.setMontant(credit.getMontant() / credit.getDureeRemboursement());
                            remboursement.setType(TypeRemboursement.MENSUALITE);
                            remboursement.setCredit(credit);
                            remboursementRepository.save(remboursement);
                            logger.info("Repayment created for credit: {}", credit.getId());
                        }
                    } catch (Exception e) {
                        logger.error("Error creating repayments for credit {}: {}", credit.getId(), e.getMessage());
                    }
                });

                logger.info("Data initialization completed successfully");
            } catch (Exception e) {
                logger.error("Error during data initialization: {}", e.getMessage());
            }
        };
    }
}