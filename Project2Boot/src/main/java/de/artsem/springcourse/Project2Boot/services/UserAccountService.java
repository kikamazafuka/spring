package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.UserAccount;
import de.artsem.springcourse.Project2Boot.repositories.UserAccountRepository;
import de.artsem.springcourse.Project2Boot.security.UserAccountDetails;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    public Optional<UserAccount> loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> foundUserAccount= userAccountRepository.findByUserAccountName(username);

        System.out.println("loadUserByUserName optional");

        return foundUserAccount;
    }
    @Transactional
    public void save(UserAccount userAccount){
        userAccountRepository.save(userAccount);
    }
}
