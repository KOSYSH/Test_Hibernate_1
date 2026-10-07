package ma.projet.beans.services;

import ma.projet.beans.entities.Contrat;
import org.hibernate.Session;
import ma.projet.beans.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class ContratService extends AbstractFacade<Contrat> {

    public ContratService() {
        super(Contrat.class);
    }


    public List<Contrat> getContratsByAssuranceType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT co FROM Contrat co JOIN co.assurance a WHERE a.type = :type",
                    Contrat.class
            ).setParameter("type", type).getResultList();
        }
    }

    public List<Contrat> getContratsActifs(LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT co FROM Contrat co WHERE co.dateFin > :date",
                    Contrat.class
            ).setParameter("date", date).getResultList();
        }
    }
}
