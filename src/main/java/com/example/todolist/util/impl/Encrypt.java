package com.example.todolist.util.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Encrypt {

  static final String ALGORITHM_TYPE = "AES/ECB/PKCS5PADDING";

  @Value("${jwt.hash-key}")
  private String secretKeyString;

  private SecretKeySpec createKey() throws Exception {
    var encryptKey = secretKeyString.getBytes(StandardCharsets.UTF_8);

    final var sha = MessageDigest.getInstance("SHA-256");

    encryptKey = sha.digest(encryptKey);
    encryptKey = Arrays.copyOf(encryptKey, 32);

    return new SecretKeySpec(encryptKey, "Rijndael");
  }

  public String encrypt(String data) throws Exception {
    final var secretKeySpec = this.createKey();

    var cipher = Cipher.getInstance(ALGORITHM_TYPE);
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

    final var dataEncrypt = data.getBytes(StandardCharsets.UTF_8);
    final var bytesEncrypt = cipher.doFinal(dataEncrypt);
    return Base64.getEncoder().encodeToString(bytesEncrypt);
  }

  public String desencrypt(String encryptedData) throws Exception {
    final var secretKeySpec = this.createKey();

    final var cipher = Cipher.getInstance(ALGORITHM_TYPE);
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

    final var dataEncrypt = Base64.getDecoder().decode(encryptedData);
    final var desencryptResult = cipher.doFinal(dataEncrypt);
    return new String(desencryptResult);
  }
}
