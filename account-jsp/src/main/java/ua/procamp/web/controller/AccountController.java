package ua.procamp.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.procamp.TestDataGenerator;
import ua.procamp.model.Account;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * This controller provides endpoint that generates a list of {@link Account} and passes it to the view.
 * <p>
 * todo: 1. Configure controller that handles requests with url "/accounts"
 * todo: 2. Inject a {@link TestDataGenerator}
 * todo: 3. Implement a method that handles GET request with param "size" to url "/accounts" and forwards it to the accounts.jsp view
 * todo: 4. In this method generate a list of account using data generator and received int value "size"
 * todo: 5. Provide a default value "10" for parameter "size"
 * todo: 6. Pass the list of accounts to the view using model attribute with name "accountList"
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

    private final TestDataGenerator testDataGenerator;

    @GetMapping
    public String getAccounts(@RequestParam(defaultValue = "10") Integer size, Model model){
        Set<Account> accounts = IntStream.range(0, size)
                .mapToObj(i -> testDataGenerator.generateAccount())
                .collect(toSet());
        model.addAttribute("accountList", accounts);
        return "accounts";
    }

}
