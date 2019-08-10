package net.koziczak.journal.service;

import net.koziczak.journal.model.User;
import net.koziczak.journal.model.dto.UserDto;
import net.koziczak.journal.repository.RoleRepository;
import net.koziczak.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    // zwraca liste wszystkicj uzytkownikow
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // rejestracja użytkownika
    public User addUser(UserDto user){

        // utwórz obiekt User
            User registered_user = new User(
                    user.getLogin(),
                    user.getEmail(),
                    passwordEncoder.encode(user.getPassword())); //# hasla
        registered_user.addRole(roleRepository.getOne(1L));
            return userRepository.save(registered_user);
    }
    // logowanie użytkownika
    public String loginUser(String email, String password){
        User user = userRepository.findFirstByEmailAndPassword(email,password);
        if(user == null){
            return "błąd logowania";
        }
        return "zarejestrowano: " + user.toString();
    }

//    public String myEmail (UserDto userDto){
//
//
//    }


}
