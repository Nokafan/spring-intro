import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

@Log4j
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setEmail("tes@i.ua");
        user.setName("testUser1");
        userService.add(user);
        userService.add(new User("Bob1", "1@i.ua"));
        userService.add(new User("Bob2", "2@i.ua"));
        userService.add(new User("Bob3", "3@i.ua"));
        userService.add(new User("Bob4", "4@i.ua"));

        userService.listUsers().forEach(log::info);
    }
}
