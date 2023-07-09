package am.hitech.service.impl;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDTO;
import am.hitech.repository.UserRepository;
import am.hitech.service.UserService;
import am.hitech.util.ErrorMessage;
import am.hitech.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserRequestDTO requestDTO) throws DuplicateException {
        if(userRepository.existsByEmail(requestDTO.getEmail())){
            throw new DuplicateException(ErrorMessage.DUPLICATE_EMAIL);
        }

        User user = convertToUser(requestDTO);
        System.out.println(user.getRole());
        userRepository.save(user);
    }
    private User convertToUser(UserRequestDTO requestDTO){
        User user = new User();
        user.setId(0);
        user.setEmail(requestDTO.getEmail());
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setPassword(requestDTO.getPassword());
        user.setStatus(requestDTO.getStatus());
        user.setRole(requestDTO.getRole());
        return user;
    }

//    private  UserResponseDTO convertToUserResponse(User user){
//        UserResponseDTO userResponseDTO = new UserResponseDTO();
//        userResponseDTO.setAge(user.getAge());
//        userResponseDTO.setEmail(user.getEmail());
//        userResponseDTO.setId(user.getId());
//        userResponseDTO.setFirstName(user.getFirstName());
//        userResponseDTO.setLastName(user.getLastName());
//        return userResponseDTO;
//    }
}
