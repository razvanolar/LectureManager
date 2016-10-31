package com.google.lecture_manager.server.utils;

import com.google.lecture_manager.server.utils.md5.MD5;

/**
 * Created by razvanolar on 31.10.2016
 */
public class ServerUtil {

  public static MD5 MD5;

  public static MD5 getMD5() throws Exception {
    if (MD5 == null)
      MD5 = new MD5();
    return MD5;
  }
}
