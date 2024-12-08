package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.Account.AccountAdmin;
import com.mgbooking.server.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.email = :Email")
    Account findByEmail(String Email);
    @Query("select a from Account a where a.email = :Email and a.otp = :otp ")
    Account checkOTP(@Param("Email") String Email, @Param("otp") String otp);
    @Query("select new com.mgbooking.server.DTOS.Account.AccountAdmin(a.id,a.fullName,a.email,a.phone,a.address,b.name) from Account a join Country b on a.countryId=b.id where a.accountType='ROLE_ADMIN'")
    List<AccountAdmin>GetAccountAdmin();
}
