package am.hitech.service.impl;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDTO;
import am.hitech.repository.UserRepository;
import am.hitech.service.UserService;
import am.hitech.util.ErrorMessage;
import am.hitech.util.exception.DuplicateException;
import am.hitech.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with such username is not found"));
    }

    @Override
    public User getById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    @Override
    public List<User> userList(Collection<? extends GrantedAuthority> authorities){
        for(GrantedAuthority grantedAuthority: authorities){
            if(grantedAuthority.getAuthority().equals("ROLE_HR") || grantedAuthority.getAuthority().equals("ROLE_PM")){
                return userRepository.findAll();
            }
        }
        return userRepository.getActiveUsers();
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

    @Override
    public List<User> search(String firstName, String lastName, String email) {
        return null;
    }

    private User convertToUser(UserRequestDTO requestDTO){
        User user = new User();
        user.setId(0);
        user.setEmail(requestDTO.getEmail());
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setPassword( passwordEncoder.encode(requestDTO.getPassword()));
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
