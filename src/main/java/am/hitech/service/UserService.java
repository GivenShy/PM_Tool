package am.hitech.service;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDTO;
import am.hitech.util.exception.DuplicateException;
import am.hitech.util.exception.NotFoundException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User getUserByUsername(String email);

    User getById(int id) throws NotFoundException;

    List<User> userList(Collection<? extends GrantedAuthority> authorities);

    void create(UserRequestDTO requestDTO) throws DuplicateException;

    public List<User> search(String firstName, String lastName, String email);
}
