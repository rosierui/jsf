package org.moonwave.jpa.bo;

import java.util.List;

import javax.persistence.Query;

import org.moonwave.jpa.model.UserRole;

/**
 * UserRoleBO
 *
 * @author moonwave
 *
 */
public class UserRoleBO extends BaseBO {

    @SuppressWarnings("unchecked")
    public List<UserRole> getAllRoles() {
        Query query = super.getEntityManager().createNamedQuery("UserRole.findAll", UserRole.class);
        List<UserRole> list = query.getResultList();
        super.release();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<UserRole> findByRole(int roleId) {
        Query query = super.getEntityManager().createNamedQuery("UserRole.findByRole", UserRole.class);
        query.setParameter("roleId", roleId);
        List<UserRole> list = query.getResultList();
        super.release();
        return list;
    }
}
