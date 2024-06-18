package devc.work.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import devc.work.dto.LoginDetailsDTO;
import devc.work.dto.LoginUserDTO;
import devc.work.dto.NewUserDTO;
import devc.work.dto.UserDTO;
import devc.work.entity.Role;
import devc.work.entity.User;
import devc.work.repository.RoleRepo;
import devc.work.repository.UserRepo;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	//use this for adding user with ADMIN roles
	@Transactional
	public User create(NewUserDTO userDTO) {
		User user = new ModelMapper().map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	//for adding normal user to DB
	@Transactional
	public User register(LoginDetailsDTO loginDetailsDTO) {
		User user = new ModelMapper().map(loginDetailsDTO, User.class);
		List<Role> roles=new ArrayList<Role>();
		roles.add(roleRepo.findByName("ROLE_USER"));
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Transactional
	public void delete(int id) {
		userRepo.deleteById(id);
	}

	@Transactional
	public void update(UserDTO userDTO) {
		User currentUser = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		currentUser.setRoles(userDTO.getRoles().stream().map(role -> roleRepo.findById(role.getId()).orElse(null))
				.filter(r -> r != null).collect(Collectors.toList()));
		userRepo.save(currentUser);
	}

	public UserDTO getById(int id) {
		// Optional <T>
		User user = userRepo.findById(id).orElse(null);
	
		if (user != null) {
			return convert(user);
		}
		return null;
	}

	@Override
	public LoginUserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepo.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		for (Role role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		LoginUserDTO currentUser = new LoginUserDTO(username, userEntity.getPassword(), authorities);

		currentUser.setId(userEntity.getId());
		currentUser.setUsername(userEntity.getUsername());

		// da hinh
		return currentUser;
	}

	public UserDTO convert(User user) {
		return new ModelMapper().map(user, UserDTO.class);
	}
}
