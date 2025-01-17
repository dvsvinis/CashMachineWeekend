package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", Float.parseFloat("500")
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", Float.parseFloat("200")
        )));

        accounts.put(3000, new PremiumAccount(new AccountData(
                3000, "Example 3", "example3@gmail.com", Float.parseFloat("600")
        )));

        accounts.put(4000, new BasicAccount(new AccountData(
                4000, "Example 4", "example4@gmail.com", Float.parseFloat("100")
        )));

        accounts.put(5000, new PremiumAccount(new AccountData(
                5000, "Example 5", "example5@gmail.com", Float.parseFloat("700")
        )));

    }

    public Integer insertBasic(String name, String email, String amount){

        Integer id = (int)(Math.random() * 6000 + 4999);//accounts.get(accounts.size()).getAccountData().getId() + 1000;
        accounts.put(id, new BasicAccount(new AccountData(
                id, name, email, Float.valueOf(amount)
        )));

        return id;
    }

    public Integer insertPremium(String name, String email, String amount){
        Integer id = (int)(Math.random() * 6000 + 4999);//accounts.get(accounts.size()).getAccountData().getId() + 1000;
        accounts.put(id, new PremiumAccount(new AccountData(
                id, name, email, Float.valueOf(amount)
        )));

        return id;
    }

    public Map<Integer, Account> getMap (){
        return accounts;
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
