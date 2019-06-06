package demo.bo.user.services;

import demo.bo.user.model.User;
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
public class UserManagementServiceTest {

    @Autowired
    private UserManagementService service;

    // precondition : create a new user (unknown email address)
    @Test
    public void test_whenCreateNewUser_thenSuccess() throws UserManagementException {

        User userToCreate = new User();
        userToCreate.setName("Joe Foo");
        userToCreate.setEmail("joe@foo.org");
        userToCreate.setGiverName("");
        userToCreate.setBirthDate(new Date());

        User createdOne = service.createUser(userToCreate);

        assertNotNull(createdOne);
        assertTrue(createdOne.getId() > 0);
    }

    @Test(expected = UserManagementException.class)
    public void test_whenCreateUserExistingEmail_thenException() throws UserManagementException {

        User userToCreate = new User();
        userToCreate.setName("Joe Foo");
        userToCreate.setEmail("joe@foo.org");
        userToCreate.setGiverName("");
        userToCreate.setBirthDate(new Date());

        User createdOne = service.createUser(userToCreate);

        assertTrue(createdOne.getId() > 0);

        service.createUser(userToCreate);
    }

    @Test
    public void test_whenGetUserUnknownId_thenNull() {

        User user = service.getUser(1);
        assertNull(user);

    }

}
