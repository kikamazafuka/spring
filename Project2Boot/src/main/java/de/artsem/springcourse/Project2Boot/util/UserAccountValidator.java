package de.artsem.springcourse.Project2Boot.util;

import de.artsem.springcourse.Project2Boot.models.Person;
import de.artsem.springcourse.Project2Boot.models.UserAccount;
import de.artsem.springcourse.Project2Boot.services.UserAccountDetailsService;
import de.artsem.springcourse.Project2Boot.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserAccountValidator implements Validator {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountValidator(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserAccount.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserAccount userAccount = (UserAccount) target;
        if (userAccountService.loadUserByUsername(userAccount.getUserAccountName()).isPresent()){
            errors.rejectValue("userAccountName", "", "This name has already taken");
        }

        System.out.println("userAccount validate");
    }
}
