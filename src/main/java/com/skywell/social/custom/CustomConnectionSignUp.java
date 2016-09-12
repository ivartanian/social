//package com.skywell.social.custom;
//
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionSignUp;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.social.facebook.api.Account;
//
///**
// * Created by viv on 12.09.2016.
// */
//public class CustomConnectionSignUp implements ConnectionSignUp {
//
//    private final AccountRepository accountRepository;
//
//    public CustomConnectionSignUp(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    public String execute(Connection<?> connection) {
//        UserProfile profile = connection.fetchUserProfile();
//        Account account = new Account(profile.getUsername(), profile.getFirstName(), profile.getLastName());
//        accountRepository.createAccount(account);
//        return account.getUsername();
//    }
//
//}