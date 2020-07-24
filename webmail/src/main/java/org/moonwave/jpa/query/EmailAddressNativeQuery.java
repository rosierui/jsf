package org.moonwave.jpa.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moonwave.jpa.model.pojo.EmailAddress;

public class EmailAddressNativeQuery 
{

    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void setUp() throws Exception {
        emf=Persistence.createEntityManagerFactory("jpa-mysql");
        em=emf.createEntityManager();
    }

    public void tearDown()throws Exception{
        em.close();
        emf.close();
    }

    @SuppressWarnings("unchecked")
    public List<EmailAddress> query() {
        Query query = em.createNativeQuery("select e.id, e.first_name as firstName, e.last_name as lastName," + 
                "e.email from email e where e.active = 1", EmailAddress.class);
        List<EmailAddress> list = (List<EmailAddress>) query.getResultList();
        return list;
    }

    public static void main( String[] args ) {
        EmailAddressNativeQuery test = new EmailAddressNativeQuery();
        try {
            test.setUp();
            List<EmailAddress> list = test.query();
            int i = 0;
            for (EmailAddress emp : list) {
                System.out.println(++i + ": " + emp.toString());
            }
            test.tearDown();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
