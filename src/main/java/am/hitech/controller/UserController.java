package am.hitech.controller;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDTO;
import am.hitech.service.UserService;
import am.hitech.util.exception.DuplicateException;
import am.hitech.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDTO requestDTO) throws DuplicateException {
        userService.create(requestDTO);
        return ResponseEntity.ok().build();
    }

    //@RolesAllowed("ROLE_HR")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(Authentication authentication){
        return ResponseEntity.ok(userService.userList(authentication.getAuthorities()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@RequestParam int id) throws NotFoundException {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public  ResponseEntity<List<User>> search(@PathVariable(name = "firstName",required = false)  String firstName,
                                              @PathVariable(name = "lastName",required = false) String lastName,
                                              @PathVariable(name = "email",required = false) String email){
        return search(firstName,lastName,email);
    }

    @GetMapping("/info")
    public ResponseEntity<User> getMyInfo(Authentication authentication){
        return ResponseEntity.ok(userService.getUserByUsername(authentication.getName()));
    }


}
