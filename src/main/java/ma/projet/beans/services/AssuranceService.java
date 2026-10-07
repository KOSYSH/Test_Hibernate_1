package ma.projet.beans.services;

import ma.projet.beans.entities.Assurance;
import org.hibernate.Session;
import ma.projet.beans.util.HibernateUtil;

import java.util.List;

public class AssuranceService extends AbstractFacade<Assurance> {

    public AssuranceService() {
        super(Assurance.class);
    }


    public List<Assurance> rechercherParType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Assurance.TypeRech", Assurance.class)
                    .setParameter("type", type)
                    .getResultList();
        }
    }
}
