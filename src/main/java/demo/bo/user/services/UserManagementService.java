package demo.bo.user.services;

import demo.bo.user.model.PermissionEnum;
import demo.bo.user.model.User;

import java.util.List;

/**
 * Service for managing users
 */
public interface UserManagementService {

    /**
     * List all users
     * @return users list or empty list if none found
     */
    List<User> listUsers();

    /**
     * List user by their family name
     * @param familyName family name
     * @return users lits or empty list if none found
     */
    List<User> listUsers(String familyName);

    /**
     * Create a new user
     * @param userToAdd user to create
     * @return created user
     * @throws UserManagementException if creation aborts, for instance if the given address email is already used
     */
    User createUser(User userToAdd) throws UserManagementException;

    /**
     * Delete an existing user
     * @param userToDelete the user to delete
     */
    void deleteUser(User userToDelete);

    /**
     * Get an existing user
     * @param userId user id
     * @return user to read or null if none found
     */
    User getUser(int userId);

    /**
     * Grant a permission to an existing user
     * @param userToGrant the user to grant
     * @param permissionType the grant type
     * @throws UserManagementException if the grant operation aborts
     */
    void grantPermission(User userToGrant, PermissionEnum permissionType) throws UserManagementException;

    /**
     * Revoke a permission to an existing user
     * @param userToRevoke the user to revoke
     * @param permissionType the grant type to revoke
     * @throws UserManagementException if the grant operation aborts
     */
    void revokePermission(User userToRevoke, PermissionEnum permissionType) throws UserManagementException;
}
