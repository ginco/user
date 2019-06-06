package demo.bo.user.api;

import demo.bo.user.model.PermissionEnum;
import demo.bo.user.model.User;
import demo.bo.user.services.UserManagementException;
import demo.bo.user.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserManagementController {

    @Autowired
    private UserManagementService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody final User user) {
        try {
            User newOne = userService.createUser(user);
            return new ResponseEntity<User>(newOne, HttpStatus.CREATED);
        } catch (UserManagementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<User>> listAll() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> find(@PathVariable("id") final int id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<User>> find(@RequestParam(name="name") final String name) {
        List<User> users = userService.listUsers(name);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") final int id) {
        User userToDelete = new User();
        userToDelete.setId(id);
        userService.deleteUser(userToDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/grant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> grant(@Valid @RequestBody final User user, @RequestParam(name="type") final String type) {
        User userToGrant = new User();
        userToGrant.setId(user.getId());
        try {
            userService.grantPermission(userToGrant, PermissionEnum.valueOf(type));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserManagementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/revoke", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> revoke(@Valid @RequestBody final User user, @RequestParam(name="type") final String type) {
        User userToGrant = new User();
        userToGrant.setId(user.getId());
        try {
            userService.revokePermission(userToGrant, PermissionEnum.valueOf(type));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserManagementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

}
