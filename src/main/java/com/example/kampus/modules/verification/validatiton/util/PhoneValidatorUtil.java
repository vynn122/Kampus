package com.example.kampus.modules.verification.validatiton.util;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidatorUtil {
    private static final String
            DEFAULT_REGION = "KH";

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public boolean isValid(String phoneNum){
        try{
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNum, DEFAULT_REGION);
            return phoneNumberUtil.isValidNumber(number);
        }catch (Exception e){
            return false;
        }
    }
}
