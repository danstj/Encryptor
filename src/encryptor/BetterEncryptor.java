
package encryptor;

/*
    Author Dan St Jean
 */
public class BetterEncryptor implements IEncryptor{
    
    public String encrypt(String strValue)
    {
        int stringLength = strValue.length();
        String strNew = "";
        
        for(int i = 0; i < stringLength; i++)
        {
            char chCurrentValue = strValue.charAt(i);
            int even = i % 2;
            
            if(even == 0)
            {
                chCurrentValue += i;
                if(i == 0)
                {
                    chCurrentValue += stringLength;
                }
            }
            else
            {
                chCurrentValue -= i;
            }
            strNew += chCurrentValue;
        }
        return strNew;
    }
    
    public String decrypt(String strValue)
    {
        int stringLength = strValue.length();
        String strNew = "";
        
        for(int i = 0; i < stringLength; i++)
        {
            char chCurrentValue = strValue.charAt(i);
            int even = i % 2;
            
            if(even == 0)
            {
                chCurrentValue -= i;
                if(i == 0)
                {
                    chCurrentValue -= stringLength;
                }
            }
            else
            {
                chCurrentValue += i;
            }
            strNew += chCurrentValue;
        }
        return strNew;
    }
}
