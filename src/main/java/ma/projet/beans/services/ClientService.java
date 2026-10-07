package ma.projet.beans.services;

import ma.projet.beans.entities.Client;
import ma.projet.beans.entities.Contrat;
import org.hibernate.Session;
import ma.projet.beans.util.HibernateUtil;

import java.util.List;

public class ClientService extends AbstractFacade<Client> {

    public ClientService() {
        super(Client.class);
    }


    public List<Contrat> getContratsByCin(String cin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Client.contratsByCin", Contrat.class)
                    .setParameter("cin", cin)
                    .getResultList();
        }
    }
}
