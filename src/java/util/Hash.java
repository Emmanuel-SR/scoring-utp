package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hash {

    private Hash() {
    }

    public static final String hashPassword(String str) {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");

            byte[] hashedPassword = md.digest(str.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
