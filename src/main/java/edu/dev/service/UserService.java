package edu.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dev.entity.User;
import edu.dev.repository.UserRepository;
import edu.dev.util.Constant;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void saveStudentUser(User user) {
		user.setRole(User.Role.STUDENT);
		userRepository.save(user);
	}

	public boolean authenticate(User user) {
		List<User> result = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (result != null && result.size() == 1) {
			return true;
		}
		return false;
	}

}
