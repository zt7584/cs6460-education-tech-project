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

	public User authenticate(User user, int role) {
		List<User> result = userRepository.findByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), role);
		if (result != null && result.size() == 1) {
			return result.get(0);
		}
		return null;
	}
}
