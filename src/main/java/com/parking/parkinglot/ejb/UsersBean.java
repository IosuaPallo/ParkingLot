package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDTO;

import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<UserDTO> findAllUsers(){
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery = entityManager.createQuery("Select u from User u", User.class);
            List<User> users = typedQuery.getResultList();
            List<UserDTO> usersDTO = new ArrayList<>();
            users.forEach((user)->{
                UserDTO userDTO = new UserDTO(user.getId(),user.getEmail(),user.getUsername());
                usersDTO.add(userDTO);
            });
            return usersDTO;
        }
        catch (Exception ex){
            throw  new EJBException(ex);
        }
    }
}
