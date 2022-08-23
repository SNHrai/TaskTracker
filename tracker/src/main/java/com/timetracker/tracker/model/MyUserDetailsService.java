package com.timetracker.tracker.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.timetracker.tracker.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG =
    "user with email %s not found";


   private UserRepository repository;
    
   @Autowired
    public MyUserDetailsService(UserRepository repository) {
    this.repository = repository;
}

@Override
public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
   User user = repository.findByUserName(userName);

   if(user == null){
    new UsernameNotFoundException(
                         String.format(USER_NOT_FOUND_MSG, userName));
   }
    return new MyUserDetails(user);

   
}





//     @Override
//     public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
                
//         Optional<User> user = repository.findByUserName(userName);
//         user.orElseThrow(() ->
//         new UsernameNotFoundException(
//                 String.format(USER_NOT_FOUND_MSG, userName)));
        
//         // return user.map(MyUserDetails::new).get();
//         return 
// }
    }
    

