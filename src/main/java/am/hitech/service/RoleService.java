package am.hitech.service;

import am.hitech.model.Role;
import am.hitech.util.RoleNotFoundException;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role create(Role role);

    void delete(int id);

    void update(Role role) throws RoleNotFoundException;

    Role getRoleById(int id) throws RoleNotFoundException;
}
