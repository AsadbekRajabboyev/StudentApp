package uz.asadbek.student.app.securityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.student.app.models.User;
import uz.asadbek.student.app.repository.UserRepo;
import uz.asadbek.student.app.util.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;


	@Autowired
	public UserDetailsService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	/*********************All user**************************************************************************************/
	//db dagi barcha userlarni List ko'rinishida olib keladi
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	/******************All user*******************************************************************************************/





	/*******************One user**************************************************************************************/
	//ID bo'yicha 1ta userni bizga olib keladi agar topilmasa UserNotFound exceptionini tashlaydi
	public User getOneUser(int id) {
		return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
	}
	/**********************One user**********************************************************************************/







	/**************************Find by username***************************************************************************/
	//Email bo'yicha db ni qidiradi agar topoplmasa "user not found" exception tashlaydi
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> byUsername = userRepo.findByUsername(username);
		if (byUsername.isEmpty()){
			throw new UsernameNotFoundException("User not Found");
		}
		System.out.println("UserDetails: " + byUsername.get());

		return new uz.asadbek.student.app.securityService.UserDetails(byUsername.get());
	}
	/*************************Find by username*************************************************************************/


	/***************************Delete by id************************************************************************/
	@Transactional
	public void getDeleteById(int id){
		System.out.println(id+" li user o'chirib tashlandi");
		userRepo.deleteById(id);
	}
	/************************Delete by id***************************************************************************/
	@Transactional
	public void registerPerson(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		user.setCreated_at(LocalDateTime.now());
		userRepo.save(user);
	}


}
