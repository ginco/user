package demo.bo.user.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="family_name")
    @NotEmpty
    private String name;

    @Column(name="given_name")
    private String giverName;

    @Temporal(TemporalType.DATE)
    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="email")
    @NotEmpty
    @Email
    private String email;

    @OneToMany
    @JoinColumn(name="user_id", referencedColumnName="id")
    private List<UserPermission> permissions;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserPermission> permissions) {
        this.permissions = permissions;
    }


}
