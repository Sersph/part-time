package com.tidc.parttimemonarch.util;

import java.sql.Date;

public class DateUtil {
   private static Date date = new Date(new java.util.Date().getTime());
   public static Date getDate(){
       return date;
   }
}

