package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDTO;

import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PasswordBean passwordBean;

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

    public void createUser(String username, String email, String password, List<String> groups) {
        LOG.info("createUser");

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);

        assignGroupsToUser(username,groups);
    }

    private void assignGroupsToUser(String username, List<String> groups) {
        LOG.info("assignGroupsToUser");

        for(String group: groups){
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

    public Collection<String> findUsernamesByUserIds(Collection<Long>userIds){
        return entityManager.createQuery("select u.username from User u where u.id in :userIds",String.class)
                .setParameter("userIds",userIds)
                .getResultList();
    }

    public UserDTO findById(Long userId) {
        User user = entityManager.createQuery("select u from User u where u.id= :id",User.class)
                .setParameter("id",userId)
                .getSingleResult();

        return new UserDTO(user.getId(),user.getEmail(),user.getUsername());
    }

    public void updateUser(Long userId, String username, String email, String password) {
        try{
            User user = entityManager.createQuery("select u from User u where u.id = :id", User.class)
                    .setParameter("id",userId)
                    .getSingleResult();
            if(user!=null){
                user.setEmail(email);
                user.setUsername(username);
                if(!password.isEmpty()){
                    user.setPassword(passwordBean.convertToSha256(password));
                }
                entityManager.merge(user);
            }
        }
        catch (EJBException ex){
            throw new EJBException(ex);
        }
    }
}
