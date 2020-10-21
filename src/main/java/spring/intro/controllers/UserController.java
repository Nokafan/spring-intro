package spring.intro.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public RedirectView insertUsers() {
        userService.add(new User("Bob1", "bob1@i.ua"));
        userService.add(new User("Bob2", "bob2@i.ua"));
        userService.add(new User("Bob3", "bob3@i.ua"));
        userService.add(new User("Bob4", "bob4@i.ua"));
        return new RedirectView("/user/all");
    }

    @GetMapping("/user/{id}")
    public UserResponseDto get(@RequestParam(name = "id") Long id) {
        User user = userService.getById(id);
        return dtoMapper(user);
    }

    @GetMapping("/user/all")
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(this::dtoMapper)
                .collect(Collectors.toList());
    }

    private UserResponseDto dtoMapper(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getName());
        return userResponseDto;
    }
}
