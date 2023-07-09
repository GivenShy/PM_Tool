package am.hitech.service.impl;

import am.hitech.model.Role;
import am.hitech.repository.RoleRepository;
import am.hitech.service.RoleService;
import am.hitech.util.exception.RoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role){
        return roleRepository.save(role);
    }

    @Override
    public void delete(int id){
        roleRepository.deleteById(id);
    }

    @Override
    public void update(Role role) throws RoleNotFoundException {
        Role role1 = roleRepository.findById(role.getId()).orElseThrow(RoleNotFoundException::new);
        role1.setRole(role.getRole());
        roleRepository.save(role1);
    }

    @Override
    public Role getRoleById(int id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }
}
