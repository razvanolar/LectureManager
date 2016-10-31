package com.google.lecture_manager.server.utils.md5;

import java.security.MessageDigest;

/**
 * Created by razvanolar on 31.10.2016
 */
public class MD5 {

  private MessageDigest digester;

  public MD5() throws Exception {
    digester = MessageDigest.getInstance("MD5");
  }

  public String crypt(String str) {
    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException("String to encript cannot be null or zero length");
    }

    digester.update(str.getBytes());
    byte[] hash = digester.digest();
    StringBuilder hexString = new StringBuilder();
    for (byte aHash : hash) {
      if ((0xff & aHash) < 0x10) {
        hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
      } else {
        hexString.append(Integer.toHexString(0xFF & aHash));
      }
    }
    return hexString.toString();
  }
}
