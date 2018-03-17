package com.learning.chatapi.ChatApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;

@Service
public class PasswordService {

    @Autowired
    SecurityService securityService;

    private final static int SALT_LENGTH = 16;



    public String saltedHashPassword(String password) {
        String salt = securityService.hexToString(generateSalt());
        return salt + "$" + securityService.hash(salt + password);
    }

    byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[SALT_LENGTH];
        random.nextBytes(bytes);
        return bytes;
    }

    public boolean checkPassword(String password, String stored) {
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                    "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = securityService.hash(saltAndPass[0] + password);
        return hashOfInput.equals(saltAndPass[1]);
    }


//    public static String sha256(String base) {
//        try{
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(base.getBytes("UTF-8"));
//            StringBuffer hexString = new StringBuffer();
//
//            for (int i = 0; i < hash.length; i++) {
//                String hex = Integer.toHexString(0xff & hash[i]);
//                if(hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//
//            return hexString.toString();
//        } catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
//
//    private static final int iterations = 20*1000;
//    private static final int saltLen = 32;
//    private static final int desiredKeyLen = 256;
//
//    /** Computes a salted PBKDF2 hash of given plaintext password
//     suitable for storing in a database.
//     Empty passwords are not supported. */
//    public static String getSaltedHash(String password) throws Exception {
//        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
//        // store the salt with the password
//        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
//    }
//
//    /** Checks whether given plaintext password corresponds
//     to a stored salted hash of the password. */
//    public static boolean check(String password, String stored) throws Exception{
//        String[] saltAndPass = stored.split("\\$");
//        if (saltAndPass.length != 2) {
//            throw new IllegalStateException(
//                    "The stored password have the form 'salt$hash'");
//        }
//        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
//        return hashOfInput.equals(saltAndPass[1]);
//    }
//
//    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
//    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
//    private static String hash(String password, byte[] salt) throws Exception {
//        if (password == null || password.length() == 0)
//            throw new IllegalArgumentException("Empty passwords are not supported.");
//        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        SecretKey key = f.generateSecret(new PBEKeySpec(
//                password.toCharArray(), salt, iterations, desiredKeyLen)
//        );
//        return Base64.encodeBase64String(key.getEncoded());
//    }
//  public byte[] generateSalt() {
//    SecureRandom random = new SecureRandom();
//    byte bytes[] = new byte[20];
//    random.nextBytes(bytes);
//    return bytes;
//}

}
