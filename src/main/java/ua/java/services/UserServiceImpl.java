package ua.java.services;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.java.classes.Sender;
import ua.java.models.Role;
import ua.java.models.User;
import ua.java.models.VerificateGenerator;
import ua.java.repository.RoleRepository;
import ua.java.repository.UserRepository;
import ua.java.repository.VerificateGeneratorRepository;

@Service
@EnableScheduling
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	@Autowired
	private VerificateGeneratorRepository verificateRep;

	@Override
	public void save(User user) {
		/*user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));*/

		Role roleUser = roleRepository.findOne(1L);
		Set<Role> roles = new HashSet<>();
		roles.add(roleUser);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	@Scheduled(fixedRate = 60000)
	public void notVerificateUserClean() {
		// TODO Auto-generated method stub
		List<VerificateGenerator> list = verificateRep.findAll();
		Timestamp serverTime = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < list.size(); i++) {
			Timestamp overdue = Timestamp.valueOf(list.get(i).getCreatedOn().toLocalDateTime().plusMinutes(180));
			if (overdue.before(serverTime)) {
				User user = list.get(i).getvUser();
				verificateRep.delete(list.get(i));
				userRepository.delete(user);
			}
		}
	}

	@Override
	public void sendVerifiEmail(User user) {
		// TODO Auto-generated method stub
		Sender sender = new Sender("TestMe.domen", "igor386453");
		VerificateGenerator verificate = new VerificateGenerator();
		verificate.generateString(20);
		verificate.setvUser(user);
		verificateRep.save(verificate);
		String message = "Hi!\nFor verificate your account you need just follow this link\n"
				+ "http://localhost:8082/Diplom/verificate/" + verificate.getVerife()
				+ "\nThe link will be available within 3 hours";

		sender.send("Your verificate letter", message, "TestMe.domen@gmail.com", user.getEmail());

	}

	@Override
	public void sendPasswordToUser(String user) {
		// TODO Auto-generated method stub
		Sender sender = new Sender("TestMe.domen", "igor386453");
		User recipient = userRepository.findByUsername(user);
		if (recipient != null) {
			String message = "Hello user!\nIt's your account details \n" + "Account nickname: "
					+ recipient.getUsername() + "\nPassword: " + recipient.getPassword();
			sender.send("Your account details from TestMe", message, "TestMe.domen@gmail.com", recipient.getEmail());
		} else {
			recipient = userRepository.findByEmail(user);
			if (recipient != null) {
				String message = "Hello user!\nIt's your account details \n" + "Account nickname: "
						+ recipient.getUsername() + "\nPassword: " + recipient.getPassword();
				sender.send("Your account details from TestMe", message, "TestMe.domen@gmail.com",
						recipient.getEmail());
			}
		}
	}

}
