package org.moonwave.jpa.bo;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GenericBO
 *
 * http://stackoverflow.com/questions/1901164/get-type-of-a-generic-parameter-in-java-with-reflection
 * http://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
 * 
 * examples:
 *    GenericBO<Semester> bo = new GenericBO<>(Semester.class);
 *    List<Semester> list = bo.findAll();
 *
 *    GenericBO<Role> bo = new GenericBO<>(Role.class);
 *    List<Role> list = bo.findAll();
 *
 * @author moonwave
 */
public class GenericBO<T> extends BaseBO {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(GenericBO.class);

    private Class<T> clazz;

    public GenericBO(Class<T> clazz) {
       this.clazz= clazz;
    }

    public Class<T> getClassType() {
        return clazz;
    }

    public List<T> findAll() {
        Query query = super.getEntityManager().createNamedQuery(clazz.getSimpleName() + ".findAll");
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        super.release();
        return list;
    }

    @SuppressWarnings("unchecked")
	public T findById(Integer id) {
        Query query = super.getEntityManager().createNamedQuery(clazz.getSimpleName() + ".findById");
        query.setParameter("id", id);
        T ret = null;
        try {
            ret = (T) query.getSingleResult();
        } catch (NoResultException e) {

        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            super.release();
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public T findById(Short id) {
        Query query = super.getEntityManager().createNamedQuery(clazz.getSimpleName() + ".findById");
        query.setParameter("id", id);
        T ret = null;
        try {
            ret = (T) query.getSingleResult();
        } catch (NoResultException e) {

        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            super.release();
        }
        return ret;
    }
}
