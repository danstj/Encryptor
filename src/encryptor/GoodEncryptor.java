
package encryptor;

/*
    Author Dan St Jean
 */
public class GoodEncryptor implements IEncryptor{
    
    
    public String encrypt(String strValue)
    {
        int stringLength = strValue.length();
        String strNew = "";
        
        for(int i = stringLength -1; i >= 0; i--)
        {
            char chCurrentValue = strValue.charAt(i);
            
            chCurrentValue = (char)~chCurrentValue;
            strNew += chCurrentValue;
        }
        return strNew;
    }
    
    public String decrypt(String strValue)
    {
        int stringLength = strValue.length();
        String strTemp = "";
        String strNew = "";
        
        for(int i = 0; i < stringLength; i++)
        {
            char chCurrentValue = strValue.charAt(i);
            
            chCurrentValue = (char)~chCurrentValue;
            strTemp += chCurrentValue;
        }
        
        for(int i = stringLength -1; i >= 0; i--)
        {
            char chCurrentValue = strTemp.charAt(i);
            strNew += chCurrentValue;
        }
        return strNew;
    }
}
