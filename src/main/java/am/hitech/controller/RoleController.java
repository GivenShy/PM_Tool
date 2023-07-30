package am.hitech.controller;

import am.hitech.model.User;
import am.hitech.model.enums.Roles;
import am.hitech.service.RoleService;
import am.hitech.util.exception.RoleNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getByRoleId(@RequestParam int id){
        return ResponseEntity.ok(Roles.values()[id]);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Roles[]> getAll(){
        return ResponseEntity.ok(Roles.values());
    }
}
