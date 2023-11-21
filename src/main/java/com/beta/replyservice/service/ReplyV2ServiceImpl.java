package com.beta.replyservice.service;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ReplyV2ServiceImpl implements ReplyV2Service {

    @Override
    public String manipulate(String val, String message) {
        String modifiedText = message;
        for(int i=0;i<val.length();i++){
            if(val.charAt(i) == '1'){
                modifiedText = reverse(modifiedText);
            }
            else if(val.charAt(i) == '2'){
                try {
                    modifiedText = encode(modifiedText);
                }
                catch (Exception e){
                    return "Error with Exception "+e;
                }
            }
            else{
                return "-1";
            }
        }
        return modifiedText;
    }

    private String reverse(String message) {
        StringBuilder reversedStringBuilder = new StringBuilder(message);
        reversedStringBuilder.reverse();
        return reversedStringBuilder.toString();
    }

    private String encode(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte[] bytes = md.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
