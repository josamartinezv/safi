package com.called.attention.service;


import com.called.attention.domain.Users;
import com.called.attention.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String documentNumber) throws UsernameNotFoundException {
        Users users = usersRepository.findByDocumentNumber(documentNumber);

        if (users == null){
            logger.error("Error en el login: No existe el usuario '" + documentNumber + "'");
            throw new UsernameNotFoundException("Error en el login: No existe el usuario '" + documentNumber + "'");
        }
        List<GrantedAuthority> authorities = users.getRols()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getDescription()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(users.getDocumentNumber(), users.getPassword(), users.getEnabled(), true, true,true, authorities);
    }
}
