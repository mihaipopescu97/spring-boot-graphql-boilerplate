package com.travelin.travelinapi.services.crud;

import com.travelin.travelinapi.dtos.entities.UserDto;
import com.travelin.travelinapi.entities.User;
import com.travelin.travelinapi.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/

@Log4j2
@Service
public class UserService implements CrudService<UserDto, User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    public User getUserById(final Long id) {
        return get(id, userRepository, User.class, log);
    }

    public User createUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return create(userDto, userRepository, modelMapper, User.class, log);
    }



    public User updateUser(final Long id, final UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return update(id, userDto, userRepository, modelMapper, User.class, log);
    }

    public Long deleteUser(Long id) {
        return delete(id, userRepository, User.class, log);
    }
}
