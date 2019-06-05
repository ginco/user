package demo.bo.user.dao;

import demo.bo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name=?1")
    List<User> listByFamilyName(String name);

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

}
