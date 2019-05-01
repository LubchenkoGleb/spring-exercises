package ua.procamp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.procamp.dao.UserDao;
import ua.procamp.model.jpa.Role;
import ua.procamp.model.jpa.RoleType;
import ua.procamp.model.jpa.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This class proovides {@link User} related service logic.
 * <p>
 * todo: 1. Configure {@link UserService} bean as spring service
 * todo: 2. Inject {@link UserDao} using constructor-based injection
 * todo: 3. Enable transaction management on class level
 * todo: 4. Configure {@link UserService#getAll()} as read-only method
 * todo: 4. Configure {@link UserService#getAllAdmins()} as read-only method
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> getAllAdmins() {
        return userDao.findAll().stream()
                .filter(u -> u.getRoles().stream().map(Role::getRoleType).anyMatch(roleType -> roleType.equals(RoleType.ADMIN)))
                .collect(toList());
    }

    public void addRole(Long userId, RoleType roleType) {
        User user = userDao.findById(userId);
        user.addRole(Role.valueOf(roleType));
    }
}
