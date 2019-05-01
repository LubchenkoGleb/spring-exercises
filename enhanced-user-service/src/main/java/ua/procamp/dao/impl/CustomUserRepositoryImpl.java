package ua.procamp.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.procamp.dao.CustomUserRepository;
import ua.procamp.model.jpa.Role;
import ua.procamp.model.jpa.RoleType;
import ua.procamp.model.jpa.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoleToAllUsers(RoleType roleType) {
        entityManager
                .createQuery("select u from User u left join fetch u.roles", User.class)
                .getResultList()
                .stream()
                .filter(u -> !u.getRoles().stream().map(Role::getRoleType).collect(Collectors.toSet()).contains(roleType))
                .forEach(u -> u.addRole(Role.valueOf(roleType)));
    }
}
