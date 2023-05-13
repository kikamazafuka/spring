package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.UserAccount;
import de.artsem.springcourse.Project2Boot.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<UserAccount> loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> foundUserAccount= userAccountRepository.findByUserAccountName(username);

        System.out.println("loadUserByUserName optional");

        return foundUserAccount;
    }
    @Transactional
    public void registrate(UserAccount userAccount){
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));

        userAccountRepository.save(userAccount);
    }
}
