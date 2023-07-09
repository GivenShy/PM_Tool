package am.hitech.service;

import am.hitech.model.dto.request.UserRequestDTO;
import am.hitech.util.exception.DuplicateException;

public interface UserService {
    void create(UserRequestDTO requestDTO) throws DuplicateException;
}
