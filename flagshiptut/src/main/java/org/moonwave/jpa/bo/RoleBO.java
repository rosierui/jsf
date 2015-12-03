package org.moonwave.jpa.bo;

import java.util.List;

import javax.persistence.Query;

import org.moonwave.jpa.model.Role;

/**
 * RoleBO
 *
 * @author moonwave
 *
 */
public class RoleBO extends BaseBO {

    private static final long serialVersionUID = 1L;

    public List<Role> getAllRoles() {
        Query query = super.getEntityManager().createNamedQuery("Role.findAll", Role.class);
        @SuppressWarnings("unchecked")
        List<Role> list = query.getResultList();
        super.release();
        return list;
    }
}
