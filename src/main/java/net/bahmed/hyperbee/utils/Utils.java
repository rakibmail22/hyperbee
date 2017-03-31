package net.bahmed.hyperbee.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zoha
 * @author rayed
 * @since 11/22/16
 */
@Component
public class Utils {

    public long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }

    public String redirectTo(String url) {
        return "redirect:" + url;
    }

    public String hashMd5(String pass) {
        try {
            byte[] passBytes = pass.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] digested = md.digest(passBytes);

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digested) {
                stringBuilder.append(Integer.toHexString(0xff & b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
    }
}
