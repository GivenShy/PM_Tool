package am.hitech.controller;

import am.hitech.model.Role;
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

    @GetMapping("/get-all")
    public ResponseEntity<List<Role>> roles(){
        return ResponseEntity.ok(roleService.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity<Role> create(@RequestBody Role role){
        return ResponseEntity.ok(roleService.create(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Role role) throws RoleNotFoundException {
        roleService.update(role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) throws RoleNotFoundException {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
}
