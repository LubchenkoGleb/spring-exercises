package ua.procamp.dao.impl;

import org.springframework.stereotype.Component;
import ua.procamp.dao.AccountDao;
import ua.procamp.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link AccountDao} implementation that is based on {@link java.util.HashMap}.
 * <p>
 * todo: 1. Configure a component with name "accountDao"
 */
@Component("accountDao")
public class InMemoryAccountDao implements AccountDao {
    private Map<Long, Account> accountMap = new HashMap<>();
    private AtomicLong autoIncrement = new AtomicLong(0);

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accountMap.values());
    }

    @Override
    public Account findById(long id) {
        return accountMap.get(id);
    }

    @Override
    public Account save(Account account) {
        Long id = autoIncrement.incrementAndGet();
        account.setId(id);
        accountMap.put(account.getId(), account);
        return account;
    }

    @Override
    public void remove(Account account) {
        accountMap.remove(account.getId());
    }

    public void clear() {
        accountMap.clear();
    }
}
