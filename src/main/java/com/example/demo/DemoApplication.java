package com.example.demo;

import com.example.demo.data.repositories.UserRepository;
import com.example.demo.data.repositories.UserRoleRepository;
import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.utils.enums.UserRoleRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo.utils.Globals.admins;
import static com.example.demo.utils.Globals.defaults;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		userRepository.deleteAll();
		userRoleRepository.deleteAll();

		User defaultUser = new User();
		defaultUser.setEmail(defaults);
		defaultUser.setPassword(passwordEncoder.encode(defaults));
		defaultUser.setFirstName(defaults);
		defaultUser.setStatus(1);
		userRepository.save(defaultUser);

		User adminUser = new User();
		adminUser.setEmail(admins);
		adminUser.setPassword(passwordEncoder.encode(admins));
		adminUser.setFirstName(admins);
		adminUser.setStatus(1);
		userRepository.save(adminUser);

		User aliceUser = new User();
		aliceUser.setEmail("alice@gmail.com");
		aliceUser.setPassword(passwordEncoder.encode("alice123"));
		aliceUser.setFirstName("Alice");
		aliceUser.setStatus(1);
		userRepository.save(aliceUser);

		User bobUser = new User();
		bobUser.setEmail("bob@gmail.com");
		bobUser.setPassword(passwordEncoder.encode("bob123"));
		bobUser.setFirstName("Bob");
		bobUser.setStatus(1);
		userRepository.save(bobUser);

		User charlieUser = new User();
		charlieUser.setEmail("charlie@gmail.com");
		charlieUser.setPassword(passwordEncoder.encode("charlie123"));
		charlieUser.setFirstName("Charlie");
		charlieUser.setStatus(1);
		userRepository.save(charlieUser);

		userRoleRepository.save(new UserRole(defaultUser.getEmail(), UserRoleRank.DEFAULT));
		userRoleRepository.save(new UserRole(adminUser.getEmail(), UserRoleRank.ADMIN));
		userRoleRepository.save(new UserRole(aliceUser.getEmail(), UserRoleRank.WORK));
		userRoleRepository.save(new UserRole(aliceUser.getEmail(), UserRoleRank.GROUP));
		userRoleRepository.save(new UserRole(bobUser.getEmail(), UserRoleRank.WORK));
		userRoleRepository.save(new UserRole(bobUser.getEmail(), UserRoleRank.MANAGE));
		userRoleRepository.save(new UserRole(bobUser.getEmail(), UserRoleRank.GROUP));
		userRoleRepository.save(new UserRole(charlieUser.getEmail(), UserRoleRank.WORK));

	}
}
