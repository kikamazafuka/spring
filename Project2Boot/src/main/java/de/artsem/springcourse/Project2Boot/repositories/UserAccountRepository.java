package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    Optional<UserAccount> findByUserAccountName(String userAccountName);
}
