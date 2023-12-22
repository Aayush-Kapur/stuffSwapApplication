package com.aayush.studentStuffSwap.studentStuffSwap.config;

import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
        private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException("nhi mil rha account tera"));
        return new ApplicationUserAccount(user);
    }
}
