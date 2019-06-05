package demo.bo.user.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_permission")
public class UserPermission {


    @EmbeddedId
    private UserPermissionId id;

    @Enumerated
    private PermissionEnum permission;

    @Temporal(TemporalType.DATE)
    private Date grantDate;

    public UserPermissionId getId() {
        return id;
    }

    public void setId(UserPermissionId id) {
        this.id = id;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public PermissionEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionEnum permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return new StringBuilder("Permission : [").append(id).append("] - Type = ").append(getPermission()).append(" - Date = ").append(getGrantDate()).toString();
    }

}
