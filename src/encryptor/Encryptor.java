
package encryptor;


public class Encryptor {


    public static void main(String[] args) {
        GoodEncryptor GE = new GoodEncryptor();
        BetterEncryptor BE = new BetterEncryptor();
        BestEncryptor bestE = new BestEncryptor();

        // Test case for Good Encryptor ========================================
        String strValue = "Hello there";
        System.out.println(strValue);
        
        strValue = GE.encrypt(strValue);
        System.out.println(strValue);
        
        strValue = GE.decrypt(strValue);
        System.out.println(strValue);
        
        // Test case for Better Encryptor ======================================
        String strValue2 = "My name is Dan";
        System.out.println(strValue2);
        
        strValue2 = BE.encrypt(strValue2);
        System.out.println(strValue2);
        
        strValue2 = BE.decrypt(strValue2);
        System.out.println(strValue2);
        
        // Test case for Best Encryptor ========================================
        String strValue3 = "What is your name?";
        System.out.println(strValue3);
        
        strValue3 = bestE.encrypt(strValue3);
        System.out.println(strValue3);
        
        strValue3 = bestE.decrypt(strValue3);
        System.out.println(strValue3);
    }
    
}
