package com.travelin.travelinapi.controllers.graphQL;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/24/2022, Sat
 **/
@Controller
public class IsAliveController {

    @QueryMapping(name = "isAlive")
    public boolean isAlive() {
        return true;
    }
}
