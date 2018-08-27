
package encryptor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
    Author Dan St Jean
 */
public class OptionsMenu implements ActionListener{
    private final int m_nWIDTH;
    private final int m_nHEIGHT;
    
    private final int m_nBTN_WIDTH;
    private final int m_nBTN_HEIGHT;
    private int m_nBtnXPos;
    private int m_nBtnYPos;
    
    private String m_strQuit;
    private String m_strEncode;
    private String m_strDecode;
    
    private String[] m_arrEncryptors;
    private final int m_nARR_INDEX;
    
    JFrame m_jFrame;
    JPanel m_jPanel;
    JButton m_jbQuit;
    JButton m_jbEncode;
    JButton m_jbDecode;
    JComboBox m_jcbEncryptorType;
    
    private String strNewValue;
    
    Properties obProperties;
    IEncryptor obIEncryptor;
    
    FileOutputStream m_obFileOut;
    FileInputStream m_obFileIn;
    
    public OptionsMenu()
    {   
        //  GUI elements    ====================================================
        m_nWIDTH = 280;
        m_nHEIGHT = 400;
        
        m_nBTN_WIDTH = 200;
        m_nBTN_HEIGHT = 50;
        m_nBtnXPos = m_nWIDTH / 2 - m_nBTN_WIDTH / 2;
        m_nBtnYPos = 100;
        
        m_strQuit = "Quit";
        m_strEncode = "Encrypt";
        m_strDecode = "Decrypt";

        m_nARR_INDEX = 3;
        m_arrEncryptors = new String[m_nARR_INDEX];
        m_arrEncryptors[0] = "encryptor.GoodEncryptor";
        m_arrEncryptors[1] = "encryptor.BetterEncryptor";
        m_arrEncryptors[2] = "encryptor.BestEncryptor";

        m_jFrame = new JFrame("Option Menu");
        m_jPanel = new JPanel();
        m_jbQuit = new JButton();
        m_jbEncode = new JButton();
        m_jbDecode = new JButton();
        m_jcbEncryptorType = new JComboBox(m_arrEncryptors);
        //  ====================================================================
        
        
        
        obProperties = new Properties();
        strNewValue = "";
        
        try
        {
            m_obFileIn = new FileInputStream("app.config");
            obProperties.load(m_obFileIn);
            setEncryptorType(obProperties.getProperty("encryptor", getDefaultEncryptorType()));
        }
        catch(FileNotFoundException exFNF)
        {
            System.out.println(exFNF);
        }
        catch(IOException exIO)
        {
            System.out.println(exIO);
        }
       
        try
        {
           Class cls = Class.forName(obProperties.getProperty("encryptor", getDefaultEncryptorType()));
           obIEncryptor = (IEncryptor)cls.newInstance();
           String strEnc = obIEncryptor.encrypt("Test");
           System.out.println(strEnc);
           System.out.println(obIEncryptor.decrypt(strEnc));
        }
        catch(ClassNotFoundException excCNF)
        {
            System.out.println(excCNF);
        }
        catch(InstantiationException excI)
        {
            System.out.println(excI);
        }
        catch(IllegalAccessException excIA)
        {
            System.out.println(excIA);
        }
    }
    
    public void createFrame()
    {
        m_jFrame.setVisible(true);
        m_jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_jFrame.setSize(m_nWIDTH, m_nHEIGHT);
        
        m_jFrame.add(m_jPanel);
        m_jPanel.setLayout(null);
        m_jPanel.setBackground(Color.BLUE);
        
        m_jPanel.add(m_jbQuit);
        m_jbQuit.setBackground(Color.yellow);
        m_jbQuit.setText(m_strQuit);
        m_jbQuit.setSize(m_nBTN_WIDTH, m_nBTN_HEIGHT);
        m_jbQuit.setLocation(m_nBtnXPos, m_nBtnYPos);
        m_jbQuit.addActionListener(this);
        
        m_jPanel.add(m_jbEncode);
        m_jbEncode.setBackground(Color.yellow);
        m_jbEncode.setText(m_strEncode);
        m_jbEncode.setSize(m_nBTN_WIDTH, m_nBTN_HEIGHT);
        m_jbEncode.setLocation(m_nBtnXPos, m_nBtnYPos + 60);
        m_jbEncode.addActionListener(this);
        
        m_jPanel.add(m_jbDecode);
        m_jbDecode.setBackground(Color.yellow);
        m_jbDecode.setText(m_strDecode);
        m_jbDecode.setSize(m_nBTN_WIDTH, m_nBTN_HEIGHT);
        m_jbDecode.setLocation(m_nBtnXPos, m_nBtnYPos + 120);
        m_jbDecode.addActionListener(this);
        
        m_jPanel.add(m_jcbEncryptorType);
        m_jcbEncryptorType.setBackground(Color.yellow);
        m_jcbEncryptorType.setSelectedIndex(0);
        m_jcbEncryptorType.setSize(m_nBTN_WIDTH, m_nBTN_HEIGHT);
        m_jcbEncryptorType.setLocation(m_nBtnXPos, m_nBtnYPos + 180);
        m_jcbEncryptorType.addActionListener(this);
        
    }
    
    public void setEncryptorType(String type) 
    {    
        obProperties.setProperty("encryptor", type);
        
        try
        {
           Class cls = Class.forName(type);
           obIEncryptor = (IEncryptor)cls.newInstance();
           FileOutputStream obFileOut = new FileOutputStream("app.config");
           obProperties.store(obFileOut, "added String");
        }
        catch(ClassNotFoundException excCNF)
        {
            System.out.println(excCNF);
        }
        catch(InstantiationException excI)
        {
            System.out.println(excI);
        }
        catch(IllegalAccessException excIA)
        {
            System.out.println(excIA);
        }
        catch(FileNotFoundException exFNF)
        {
            System.out.println(exFNF);
        }
        catch(IOException exIO)
        {
            System.out.println(exIO);
        }
        
    }
    
    public String getDefaultEncryptorType() { return  "encryptor.GoodEncryptor"; }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String selection = (String)m_jcbEncryptorType.getSelectedItem();
        String strValue = "";
        
        if (source != m_jcbEncryptorType)
        {
            if(source == m_jbQuit)
            {
                System.exit(0);
            }
            if(source == m_jbEncode)
            {
                acceptString();           
            }
            if(source == m_jbDecode)
            {
                stringdecrypt();
            }
        }
        else
        {
            if(selection == "encryptor.GoodEncryptor")
            {
                setEncryptorType(selection);
                System.out.println(selection);
            }
            else if(selection == "encryptor.BetterEncryptor")
            {
                setEncryptorType(selection);
                System.out.println(selection);
            }
            else if(selection == "encryptor.BestEncryptor")
            {
                setEncryptorType(selection);
                System.out.println(selection);
            }
        }
        
    }
    
    public void acceptString()
    {
        String strValue;
        strValue = JOptionPane.showInputDialog(null, "Enter the word or phrase you\n want to Encrypt");
        
        strNewValue = obIEncryptor.encrypt(strValue);
        obProperties.setProperty("phrase", strValue);   // ADD <<===============
        try
        {
            obProperties.store(m_obFileOut, null);
            m_obFileOut.close();
        }
        catch(IOException exIO)
        {
            
        }
        
        JOptionPane.showMessageDialog(null, "Your Encrypted word or phrase:\n" + strNewValue);
    }
    
    public void stringdecrypt()
    {
        if(strNewValue == "")
        {
            JOptionPane.showMessageDialog(null, "The String is empty, Nothing to decrypt");
        }
        else
        {
            strNewValue = obIEncryptor.decrypt(strNewValue);
        
            JOptionPane.showMessageDialog(null, strNewValue);
            strNewValue = "";
        }
        
    }
}
