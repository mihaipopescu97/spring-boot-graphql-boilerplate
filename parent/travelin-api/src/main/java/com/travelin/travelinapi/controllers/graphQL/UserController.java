package com.travelin.travelinapi.controllers.graphQL;

import com.travelin.travelinapi.dtos.entities.UserDto;
import com.travelin.travelinapi.entities.User;
import com.travelin.travelinapi.services.crud.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/24/2022, Sat
 **/

@Log4j2
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @QueryMapping(name = "userById")
    public User getUserById(@Argument final Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping(name = "createUser")
    public User createUser(@Argument(name = "user") final UserDto userDto) {
        return userService.createUser(userDto);
    }

    @MutationMapping(name = "updateUser")
    public User updateUser(@Argument(name = "id") final Long id, @Argument(name = "user") final UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @MutationMapping(name = "deleteUser")
    public Long deleteUser(@Argument(name = "id") final Long id) {
        return userService.deleteUser(id);
    }
}
