package com.learning.mailDemo;

import java.util.Random;

//��ȡö�����������  
public class Enums {
  public static <T extends Enum<T>> T random(Class<T> ec){  
      return random(ec.getEnumConstants());  
  }
  
  public static <T> T random(T[] values){  
      //ÿ�ε���newһ����random����ʶҪ��һ����Ҫ��Ȼÿ�����еĽ����һ����  
      return values[new Random(System.nanoTime()).nextInt(values.length)];  
  }  
}  
