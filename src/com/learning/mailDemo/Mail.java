package com.learning.mailDemo;

import java.util.Iterator;

class Mail {
    enum GeneralDelivery {  
        NO, OK1, OK2, OK3, OK4, OK5  
    };  
  
    enum Scannability {  
        // 信件无法扫描  
        UNSCANNABLE, YES1, YES2, YES3, YES4  
    };  
  
    enum Readablility {  
        // 字迹模糊不清  
        ILLEGIBLE, YES1, YES2, YES3, YES4  
    };  
  
    enum Address {  
        // 寄件地址错误  
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6  
    };  
  
    enum ReturnAddress {  
        // 返件地址模糊不清  
        MISSING, OK1, OK2, OK3, OK4, OK5  
    };  
  
    // 取邮件  
    GeneralDelivery generalDelivery;  
    // 扫描邮件  
    Scannability scannability;  
    // 信件可读性  
    Readablility readablility;  
    // 地址  
    Address address;  
    // 退件地址  
    ReturnAddress returnAddress;  
    // 信件计数  
    static long counter = 0;  
    // 信件ID  
    long id = counter++;  
  
    public String toString() {  
        return "Mail" + id;  
    }  
  
    public String details() {  
        return toString() + ",General Delivery: " + generalDelivery  
                + ",Address Scanability: " + scannability  
                + ",Address Readablility: " + readablility  
                + ",Address address: " + address + ",Return address: "  
                + returnAddress;  
    }  
  
    // 创建一封信件  
    public static Mail randomMail() {  
        Mail m = new Mail();  
        m.generalDelivery = Enums.random(GeneralDelivery.class);  
        m.scannability = Enums.random(Scannability.class);  
        m.readablility = Enums.random(Readablility.class);  
        m.address = Enums.random(Address.class);  
        m.returnAddress = Enums.random(ReturnAddress.class);  
        return m;  
    }  
  
    // 创建count个邮件迭代集合  
    public static Iterable<Mail> generator(final int count) {  
        return new Iterable<Mail>() {  
            int n = count;  
  
            @Override  
            public Iterator<Mail> iterator() {  
                return new Iterator<Mail>() {  
                    @Override  
                    public boolean hasNext() {  
                        return n-- > 0;  
                    }  
  
                    @Override  
                    public Mail next() {  
                        return randomMail();  
                    }  
  
                    @Override  
                    public void remove() {  
                        throw new UnsupportedOperationException();  
                    }  
                };  
            }  
        };  
    }  
} 