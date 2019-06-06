package demo.bo.user.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserVO implements Serializable {

    private int id;
    private String name;
    private String givenName;
    private Date birthDate;
    private List<String> permissions;


}
