package demo.bo.user.dao;

import demo.bo.user.model.PermissionEnum;
import demo.bo.user.model.User;
import demo.bo.user.model.UserPermission;
import demo.bo.user.model.UserPermissionId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserPermissionDao permDao;

    @Test
    public void test_whenSaveUser_thenIdUserOK() {

        User userToAdd = new User();
        userToAdd.setBirthDate(new Date());
        userToAdd.setEmail("titi@toto.org");
        userToAdd.setGiverName("firstName");
        userToAdd.setName("titi toto");

        dao.save(userToAdd);

        assertTrue(userToAdd.getId() > 0);

    }

    @Test
    public void test_whenUpdateUser_thenSuccess() {

        User userToAdd = new User();
        userToAdd.setBirthDate(new Date());
        userToAdd.setEmail("titi@toto.org");
        userToAdd.setGiverName("firstName");
        userToAdd.setName("titi toto");

        dao.save(userToAdd);

        User userToUpdate = dao.getOne(userToAdd.getId());
        userToUpdate.setEmail("toto@titi.org");

        dao.saveAndFlush(userToUpdate);

        assertTrue("toto@titi.org".equals(dao.getOne(userToAdd.getId()).getEmail()));
    }

    @Test
    public void test_whenGrantPermissionToUser_thenSuccess() {

        User userToGrant = new User();
        dao.save(userToGrant);

        UserPermissionId firstPermissionId = new UserPermissionId();
        firstPermissionId.setUserId(userToGrant.getId());

        UserPermission firstPermission = new UserPermission();
        firstPermission.setGrantDate(new Date());
        firstPermission.setPermission(PermissionEnum.ADMIN);
        firstPermission.setId(firstPermissionId);

        permDao.saveAndFlush(firstPermission);

    }

    @Test
    public void test_WhenFindUserByEmailNotFound_thenSuccess() {

        User user = dao.findByEmail("joe@foo.org");

        assertNull(user);
    }
}
