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

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Query query = super.getEntityManager().createNamedQuery("User.findAll", User.class);
        List<User> list = query.getResultList();
        super.release();
        return list;
    }

    public User findById(Integer userId) {
        Query query = super.getEntityManager().createNamedQuery("User.findById", User.class);
        query.setParameter("id", userId);
        User list = (User) query.getSingleResult();
        super.release();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<User> findInIds(List<Integer> userIds) {
    	String q1 = "SELECT u FROM User u WHERE u.id in (";
    	String q2 = ")";
        StringBuilder sb = new StringBuilder();
        sb.append(q1);
        for (int i = 0; i < userIds.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(userIds.get(i));
        }
        sb.append(q2);
        Query query = super.getEntityManager().createQuery(sb.toString(), User.class);
//        query.setParameter("ids", sb.toString());
//        query.setParameter("ids", userIds);
        List<User> list = query.getResultList();
        super.release();
        return list;
    }
}
