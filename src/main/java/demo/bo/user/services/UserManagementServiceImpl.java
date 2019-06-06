package demo.bo.user.services;

import demo.bo.user.dao.UserDao;
import demo.bo.user.dao.UserPermissionDao;
import demo.bo.user.model.PermissionEnum;
import demo.bo.user.model.User;
import demo.bo.user.model.UserPermission;
import demo.bo.user.model.UserPermissionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPermissionDao userPermissionDao;

    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    public List<User> listUsers(String familyName) {

        if (StringUtils.isEmpty(familyName)) {
            throw new IllegalArgumentException("family name must be provided");
        }

        return userDao.listByFamilyName(familyName);
    }

    @Override
    public User createUser(User userToAdd) throws UserManagementException {

        if (userToAdd == null) {
            throw new IllegalArgumentException("user to create must be provided");
        }

        if (userDao.findByEmail(userToAdd.getEmail()) != null) {
            throw new UserManagementException("email address already in use");
        }

        userDao.save(userToAdd);

        return userToAdd;
    }

    @Override
    public void deleteUser(User userToDelete) {

        if (userToDelete == null) {
            throw new IllegalArgumentException("user to delete must be provided");
        }

        userDao.delete(userToDelete);
        if (userToDelete.getPermissions()!=null && ! userToDelete.getPermissions().isEmpty()) {
            userPermissionDao.deleteAll(userToDelete.getPermissions()); //or use a cascade
        }
    }

    @Override
    public User getUser(int userId) {

        if (userId < 1) {
            throw new IllegalArgumentException("user id must be provided");
        }
        Optional<User> user = userDao.findById(userId);
        return (user.isPresent() ? user.get() : null);
    }

    @Override
    public void grantPermission(User userToGrant, PermissionEnum permissionType) throws UserManagementException {

        if (userToGrant == null) {
            throw new IllegalArgumentException("user to grant must be provided");
        }

        if (permissionType == null) {
            throw new IllegalArgumentException("permission to grant must be provided");
        }

        // check the user exists and that he doesn't already have the granted permission
        Optional<User> user = userDao.findById(userToGrant.getId());
        if (! user.isPresent()) {
            throw new UserManagementException("Unable to grant permission to this user : he doesn't exist anymore");
        }
        for (UserPermission permission : user.get().getPermissions()) {
            if (permission.getPermission().equals(permissionType)) {
                throw new UserManagementException("Permission already granted");
            }
        }

        // grant the new permission
        UserPermissionId permissionId = new UserPermissionId();
        permissionId.setUserId(userToGrant.getId());
        UserPermission permission = new UserPermission();
        permission.setId(permissionId);
        permission.setPermission(permissionType);
        permission.setGrantDate(new Date());

        userPermissionDao.save(permission);
    }

    @Override
    public void revokePermission(User userToRevoke, PermissionEnum permissionType) throws UserManagementException {

        if (userToRevoke == null) {
            throw new IllegalArgumentException("user to grant must be provided");
        }

        if (permissionType == null) {
            throw new IllegalArgumentException("permission to grant must be provided");
        }

        // check the user exists and that he doesn't already have the granted permission
        User user = userDao.getOne(userToRevoke.getId());
        if (user == null) {
            throw new UserManagementException("Unable to grant permission to this user : he doesn't exist anymore");
        }
        for (UserPermission permission : user.getPermissions()) {
            if (permission.getPermission().equals(permissionType)) {
                userPermissionDao.delete(permission);
            }
        }
    }


}
