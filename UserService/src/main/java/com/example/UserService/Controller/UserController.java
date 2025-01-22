//package com.example.UserService.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class UserController {
//    @GetMapping("/id")
//    pubilc String getID(@RequestParam String id){
//        return id;
//    }
//}

package com.example.UserService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/user")
//public class UserController {
//    @GetMapping("/id")
//    public String getID(@RequestParam String id) {
//        return id;
//    }
//}



@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/id")
    public String getUserById(@RequestParam String id) {
        return "User ID: " + id;
    }
}
