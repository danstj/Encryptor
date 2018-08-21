
package encryptor;

import java.util.concurrent.ThreadLocalRandom;

/*
    Author Dan St Jean
 */
public class BestEncryptor {
    
    public String encrypt(String strValue)
    {
        int stringLength = strValue.length();
        String strNew = "";
        
        for(int i = 0; i < stringLength; i++)
        {
            char chCurrentValue = strValue.charAt(i);
            char chNumValue;
            int nIncrementValue = getRandomNum();
            
            chCurrentValue += nIncrementValue;
            chNumValue = (char)((char)nIncrementValue + 48);

            if(i % 3 == 0)
            {
                int offset = 9;
                chNumValue -= offset;
            }
            else if(i % 2 == 0)
            {
                int offset = 16;
                chNumValue += offset;
            }
            else
            {
                int offset = 48;
                chNumValue += offset;
            }
            strNew += chNumValue;
            strNew += chCurrentValue;
        }      
        return strNew;
    }
    
    public String decrypt(String strValue)
    {
        int stringLength = strValue.length();
        int nIncrementValue = 0;
        String strNew = "";
        
        for(int i = 0; i < stringLength - 1; i += 2, nIncrementValue++)
        {
            char chCurrentValue = strValue.charAt(i);
            char chLetter = strValue.charAt(i + 1);
            int nDecrementValue = 0;
            
            if(nIncrementValue % 3 == 0)
            {
                int offset = 9;
                chCurrentValue += offset;
            }
            else if(nIncrementValue % 2 == 0)
            {
                int offset = 16;
                chCurrentValue -= offset;
            }
            else
            {
                int offset = 48;
                chCurrentValue -= offset;
            }
            nDecrementValue =  (int)chCurrentValue - 48;
            
            chLetter -= nDecrementValue;
            strNew += chLetter;
        }
        return strNew;
    }
    
    private int getRandomNum()
    {
        int min = 1;
        int max = 9;
        int nRandNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        
        return nRandNum;
    }
}
