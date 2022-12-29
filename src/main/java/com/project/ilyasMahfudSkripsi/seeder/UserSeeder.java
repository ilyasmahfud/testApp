package com.project.ilyasMahfudSkripsi.seeder;

import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserRepository;
import com.project.ilyasMahfudSkripsi.module.authentication.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
@Slf4j
public class UserSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void run(String... args) {
        try {
            seed();
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    private void seed() {
        if (userRepository.count() == 0) {
            User userTony = new User();
            userTony.setUsername("tonystark");
            userTony.setPassword("password123");

            UserDetails userDetailsTony = new UserDetails();
            userDetailsTony.setFullName("Tony Stark");

            authenticationService.register(userTony, userDetailsTony);

            User userBruce = new User();
            userBruce.setUsername("brucewayne");
            userBruce.setPassword("password123");

            UserDetails userDetailsBruce = new UserDetails();
            userDetailsBruce.setFullName("Bruce Wayne");

            authenticationService.register(userBruce, userDetailsBruce);
        }
    }
}
