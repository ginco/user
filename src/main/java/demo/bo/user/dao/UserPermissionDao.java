package demo.bo.user.dao;

import demo.bo.user.model.UserPermission;
import demo.bo.user.model.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionDao extends JpaRepository<UserPermission, UserPermissionId> {
}
