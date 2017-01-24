package com.google.lecture_manager.server.utils;

import com.google.lecture_manager.server.utils.md5.MD5;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by razvanolar on 31.10.2016
 */
public class ServerUtil {

  private static MD5 MD5;
  public static SessionFactory SESSION_FACTORY;

  public static MD5 getMD5() throws Exception {
    if (MD5 == null)
      MD5 = new MD5();
    return MD5;
  }

  @SuppressWarnings("deprecation")
  public static void initSessionFactory() throws Exception {
    SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
  }
}
