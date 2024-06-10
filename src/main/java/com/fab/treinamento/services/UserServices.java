package com.fab.treinamento.services;

import com.fab.treinamento.exceptions.ResourceNotFoundException;
import com.fab.treinamento.model.User;
import com.fab.treinamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {

//        logger.info("finding one person!");
//        Person person = new Person();
//        person.setAddress("Rua Cedro");
//        person.setFirstName("Guilherme");
//        person.setLastName("Santos");
//        person.setGender("Male");

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name" + username + "!");
        var user = userRepository.findByUserName(username);
        if (user != null) {
            return (UserDetails) user;
        } else {
            throw new UsernameNotFoundException("UserName" + username + "not found!");
        }
    }
}
