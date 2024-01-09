package com.parking.parkinglot.ejb;

import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserGroupsBean {
    private static final Logger LOG = Logger.getLogger(UserGroupsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public boolean hasInvoicingGroup(String username){
        LOG.info("find if user has Invoicing Group");
        try{
            List<UserGroup> userGroups = entityManager.createQuery("select ug from UserGroup ug where ug.username= :username and ug.userGroup= :userGroup",UserGroup.class)
                    .setParameter("username",username)
                    .setParameter("userGroup","INVOICING")
                    .getResultList();
            return !userGroups.isEmpty();
        }
        catch (EJBException ex){
            throw  new EJBException(ex.getMessage());
        }
    }
}
