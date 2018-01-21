package com.learning.mailDemo;

public class PostOffice {
	enum MailHandler {  
        GENERAL_DELIVERY {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.generalDelivery) {  
                case NO:  
                    System.out.println("�����ʼ�(" + m + ")ʧ�ܣ�");  
                    return false;  
                default:  
                    System.out.println("�����ʼ�(" + m + ")�ɹ���");  
                    return true;  
                }  
            }  
  
        },  
        MACHINE_SCAN {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.scannability) {  
                case UNSCANNABLE:  
                    System.out.println("����ɨ���ʼ��ļ���ַ(" + m + ")ʧ�ܣ�");  
                    return false;  
                default:  
                    System.out.println("����ɨ���ʼ��ļ���ַ(" + m+ ")�ɹ���");  
                    switch (m.address) {  
                    case INCORRECT:  
                        System.out.println("�ʼ�(" + m + ")�ļ���ַ����");  
                        return false;  
                    default:  
                        System.out.println("�ʼ�(" + m+ ")�ļ���ַ��ȷ��");  
                        return true;  
                    }  
                }  
            }  
  
        },  
        VISUAL_INSPECTION {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.readablility) {  
                case ILLEGIBLE:  
                    System.out.println("�˹��˲��ʼ�(" + m + ")�ļ���ַ���ִ���");  
                    return false;  
                default:  
                    System.out.println("�˹��˲��ʼ�(" + m + ")�ļ���ַ������");  
                    return true;  
                }  
            }  
        },  
        RETURN_TO_SEND {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.returnAddress) {  
                case MISSING:  
                    System.out.println("�˲��ʼ�(" + m + ")������ַ�����ּ�ģ�����壡");  
                    return false;  
                default:  
                    System.out.println("�˲��ʼ�(" + m + ")������ַ���������");  
                    return true;  
                }  
            }};  
        abstract boolean handle(Mail m);  
    }  

	//ģ�ⷢ�ʼ�  
    static void handle(Mail m){  
        for(MailHandler handler : MailHandler.values()){  
            if(!handler.handle(m)){
                System.out.println("�ʼ�"+m+" ��һ�����ţ�");  
                return;  
            }else continue;  
        }
        System.out.println("�ʼ�"+m+" ���������ͣ�");  
    }
    
    public static void main(String[] args){
        for(Mail mail : Mail.generator(10)){
        	System.out.println(mail.details());
            handle(mail);
            System.out.println("****************");
        }
    }
}
