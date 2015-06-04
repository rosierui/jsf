/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Jonathan Luo
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 */

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
    
    public void setUp() throws Exception{
        emf=Persistence.createEntityManagerFactory("jpa-mysql");
        em=emf.createEntityManager();
    }
    
    public void tearDown()throws Exception{
        em.close();
        emf.close();
    }

    @SuppressWarnings("unchecked")
    public List<EmailAddress> query(){
        Query query = em.createNativeQuery("select e.id, e.first_name as firstName, e.last_name as lastName," + 
                "e.email from email e", EmailAddress.class);
        query.setMaxResults(4);
        List<EmailAddress> list = (List<EmailAddress>) query.getResultList();
        return list;
    }

    public static void main( String[] args )
    {
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
