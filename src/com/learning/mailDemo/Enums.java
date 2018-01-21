package com.learning.mailDemo;

import java.util.Random;

//获取枚举中随机属性  
public class Enums {
  public static <T extends Enum<T>> T random(Class<T> ec){  
      return random(ec.getEnumConstants());  
  }
  
  public static <T> T random(T[] values){  
      //每次调用new一个新random，标识要不一样，要不然每次运行的结果都一样。  
      return values[new Random(System.nanoTime()).nextInt(values.length)];  
  }  
}  
