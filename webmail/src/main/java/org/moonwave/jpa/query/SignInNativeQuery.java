package org.moonwave.jpa.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moonwave.util.Md5Util;

public class SignInNativeQuery 
{
    private EntityManagerFactory emf;
    private EntityManager em;

    public void setUp() throws Exception {
        emf=Persistence.createEntityManagerFactory("jpa-mysql");
        em=emf.createEntityManager();
    }

    public void tearDown()throws Exception{
        em.close();
        emf.close();
    }

    @SuppressWarnings("unchecked")
    public boolean signIn(String userId, String password) throws Exception {
        setUp();
        String encryptPassword = Md5Util.encryptPassword(password);
        Query query = em.createNativeQuery("select count(*) from user where user_id = ? and password = ?");
        query.setParameter(1, userId);
        query.setParameter(2, encryptPassword);
        List<Number> counts = (List<Number>) query.getResultList();
        long count = 0;
        if (counts.size() > 0) {
            count = counts.get(0).longValue();
        }
        tearDown();
        return (count == 1) ? true : false;
    }

    public static void main( String[] args ) {
        SignInNativeQuery test = new SignInNativeQuery();
        try {
            test.setUp();
            boolean ret = test.signIn("phxcnwrd", "hope1");
            test.tearDown();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
