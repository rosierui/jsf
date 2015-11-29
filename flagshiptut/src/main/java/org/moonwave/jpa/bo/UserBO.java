package org.moonwave.jpa.bo;

import java.util.List;

import javax.persistence.Query;

import org.moonwave.jpa.model.User;

/**
 * UserBO
 *
 * @author moonwave
 *
 */
public class UserBO extends BaseBO {

    public List<User> getAllUsers() {
        Query query = super.getEntityManager().createNamedQuery("User.findAll", User.class);
        @SuppressWarnings("unchecked")
        List<User> list = query.getResultList();
        super.release();
        return list;
    }
}
