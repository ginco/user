package demo.bo.user.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserPermissionId implements Serializable {

    @Column(name="permission_id")
    private String permissionId;

    @Column(name="user_id")
    private int userId;

    public UserPermissionId() {

    }

    public UserPermissionId(int userId) {
        permissionId = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionId that = (UserPermissionId) o;
        return permissionId == that.permissionId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, userId);
    }
}
