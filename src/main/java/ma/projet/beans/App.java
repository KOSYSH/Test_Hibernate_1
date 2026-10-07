package ma.projet.beans;

import ma.projet.beans.entities.Assurance;
import ma.projet.beans.entities.Client;
import ma.projet.beans.entities.Contrat;
import ma.projet.beans.entities.StatutContrat;
import ma.projet.beans.services.AssuranceService;
import ma.projet.beans.services.ClientService;
import ma.projet.beans.services.ContratService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        AssuranceService assuranceService = new AssuranceService();
        ClientService clientService = new ClientService();
        ContratService contratService = new ContratService();


        Assurance a1 = new Assurance("Sante", 1200.0, "Hospita");
        Assurance a2 = new Assurance("Auto", 800.0, "tous risques");
        System.out.println("Create asurrance a1 : " + assuranceService.create(a1));
        System.out.println("Create asurrance a2 : " + assuranceService.create(a2));

        Client c1 = new Client("FL00000", "BOUDAD", "Yasser", "jYasserBOUDAD@gmail.com", "0624568772");
        Client c2 = new Client("f00000", "BOUDAD", "Yasser", "BDDYasser@proton.me", "0617864665");
        System.out.println("creer Client c1 : " + clientService.create(c1));
        System.out.println("creer Client c2 : " + clientService.create(c2));

        Contrat co1 = new Contrat(LocalDate.of(2026, 1, 1), LocalDate.of(2027, 12, 31), StatutContrat.ACTIF);
        co1.setClient(c1); co1.setAssurance(a1);

        Contrat co2 = new Contrat(LocalDate.of(2025, 1, 15), LocalDate.of(2026, 6, 15), StatutContrat.RESILIE);
        co2.setClient(c1); co2.setAssurance(a2);

        Contrat co3 = new Contrat(LocalDate.of(2026, 3, 1), LocalDate.of(2027, 3, 1), StatutContrat.ACTIF);
        co3.setClient(c2); co3.setAssurance(a1);

        System.out.println("creer Contrat co1 : " + contratService.create(co1));
        System.out.println("creer Contrat co2 : " + contratService.create(co2));
        System.out.println("creer Contrat co3 : " + contratService.create(co3));

     
        System.out.println(" findAll Assuranes ");
        assuranceService.findAll().forEach(a -> System.out.println("  " + a.getId() + " | " + a.getType() + " | " + a.getMontnat()));

        System.out.println(" findAll Clients ");
        clientService.findAll().forEach(c -> System.out.println("  " + c.getId() + " | " + c.getCin() + " | " + c.getNom()));

        System.out.println(" findAll Contrats ");
        contratService.findAll().forEach(co -> System.out.println("  " + co.getId() + " | " + co.getDateDebut() + " -> " + co.getDateFin() + " | " + co.getStaut()));

        System.out.println(" findById Assurance ");
        Assurance found = assuranceService.findById(a1.getId());
        if (found != null) System.out.println("  Found: " + found.getType());

        a1.setMontnat(1500.0);
        System.out.println(" update asurance a1 montant 500 : " + assuranceService.update(a1));

        System.out.println(" 1. rechercherParType(Sante) ");
        assuranceService.rechercherParType("Sante")
                .forEach(a -> System.out.println("  " + a.getId() + " | " + a.getType() + " | " + a.getMontnat()));

        System.out.println(" 2. getContratsByCin(FL00000) ");
        clientService.getContratsByCin("FL00000")
                .forEach(co -> System.out.println("  " + co.getId() + " | " + co.getDateDebut() + " -> " + co.getDateFin() + " | " + co.getStaut()));

        System.out.println(" 3. getContratsByAssuranceType(Sante) ");
        contratService.getContratsByAssuranceType("Sante")
                .forEach(co -> System.out.println("  " + co.getId() + " | " + co.getDateDebut() + " -> " + co.getDateFin()));

        System.out.println(" 4. getContratsActifs(today=" + LocalDate.now() + ") ");
        contratService.getContratsActifs(LocalDate.now())
                .forEach(co -> System.out.println("  " + co.getId() + " | " + co.getDateFin() + " | " + co.getStaut()));

        System.out.println("delete Contrat co2 : " + contratService.delete(co2));
        System.out.println("delete Contrat co3 : " + contratService.delete(co3));  // delete co3 before c2
        System.out.println(" delete Client c2  : " + clientService.delete(c2));
        System.out.println(" delete Assurance a2 : " + assuranceService.delete(a2));

        System.out.println(" findAll Contrats apres delete ");
        contratService.findAll().forEach(co -> System.out.println("  " + co.getId() + " | " + co.getDateDebut() + " -> " + co.getDateFin()));
    }
}
