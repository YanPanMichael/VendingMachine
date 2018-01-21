package com.learning.mailDemo;

public class PostOffice {
	enum MailHandler {  
        GENERAL_DELIVERY {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.generalDelivery) {  
                case NO:  
                    System.out.println("揽收邮件(" + m + ")失败！");  
                    return false;  
                default:  
                    System.out.println("揽收邮件(" + m + ")成功！");  
                    return true;  
                }  
            }  
  
        },  
        MACHINE_SCAN {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.scannability) {  
                case UNSCANNABLE:  
                    System.out.println("机器扫描邮件寄件地址(" + m + ")失败！");  
                    return false;  
                default:  
                    System.out.println("机器扫描邮件寄件地址(" + m+ ")成功！");  
                    switch (m.address) {  
                    case INCORRECT:  
                        System.out.println("邮件(" + m + ")寄件地址错误！");  
                        return false;  
                    default:  
                        System.out.println("邮件(" + m+ ")寄件地址正确！");  
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
                    System.out.println("人工核查邮件(" + m + ")寄件地址发现错误！");  
                    return false;  
                default:  
                    System.out.println("人工核查邮件(" + m + ")寄件地址正常！");  
                    return true;  
                }  
            }  
        },  
        RETURN_TO_SEND {  
            @Override  
            public boolean handle(Mail m) {  
                switch (m.returnAddress) {  
                case MISSING:  
                    System.out.println("核查邮件(" + m + ")返件地址发现字迹模糊不清！");  
                    return false;  
                default:  
                    System.out.println("核查邮件(" + m + ")返件地址结果正常！");  
                    return true;  
                }  
            }};  
        abstract boolean handle(Mail m);  
    }  

	//模拟发邮件  
    static void handle(Mail m){  
        for(MailHandler handler : MailHandler.values()){  
            if(!handler.handle(m)){
                System.out.println("邮件"+m+" 是一封死信！");  
                return;  
            }else continue;  
        }
        System.out.println("邮件"+m+" 被正常发送！");  
    }
    
    public static void main(String[] args){
        for(Mail mail : Mail.generator(10)){
        	System.out.println(mail.details());
            handle(mail);
            System.out.println("****************");
        }
    }
}
