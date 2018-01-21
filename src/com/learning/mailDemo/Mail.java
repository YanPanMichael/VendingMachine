package com.learning.mailDemo;

import java.util.Iterator;

class Mail {
    enum GeneralDelivery {  
        NO, OK1, OK2, OK3, OK4, OK5  
    };  
  
    enum Scannability {  
        // �ż��޷�ɨ��  
        UNSCANNABLE, YES1, YES2, YES3, YES4  
    };  
  
    enum Readablility {  
        // �ּ�ģ������  
        ILLEGIBLE, YES1, YES2, YES3, YES4  
    };  
  
    enum Address {  
        // �ļ���ַ����  
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6  
    };  
  
    enum ReturnAddress {  
        // ������ַģ������  
        MISSING, OK1, OK2, OK3, OK4, OK5  
    };  
  
    // ȡ�ʼ�  
    GeneralDelivery generalDelivery;  
    // ɨ���ʼ�  
    Scannability scannability;  
    // �ż��ɶ���  
    Readablility readablility;  
    // ��ַ  
    Address address;  
    // �˼���ַ  
    ReturnAddress returnAddress;  
    // �ż�����  
    static long counter = 0;  
    // �ż�ID  
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
  
    // ����һ���ż�  
    public static Mail randomMail() {  
        Mail m = new Mail();  
        m.generalDelivery = Enums.random(GeneralDelivery.class);  
        m.scannability = Enums.random(Scannability.class);  
        m.readablility = Enums.random(Readablility.class);  
        m.address = Enums.random(Address.class);  
        m.returnAddress = Enums.random(ReturnAddress.class);  
        return m;  
    }  
  
    // ����count���ʼ���������  
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