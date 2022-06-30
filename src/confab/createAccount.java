package confab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class createAccount extends JFrame {

	// Declaring Global Variables
	private JPanel contentPane;
	private JTextField username;
	private JTextField chat_username;
	private JLabel lblChatId;
	private JLabel lblPassword;
	private JLabel lblPasswordAgain;
	private JPasswordField password;
	private JPasswordField repassword;
	private JButton main_menu;
	private Panel panel;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/chatapplication";
    static final String USER = "root";
    static final String PASS = "";
    static String sql;
    static String sql2;
    static ResultSet rs;
    static ResultSet rs2;
    static ResultSet rs1;
    static ResultSet rs3;
    private JTextField email;
    private JLabel lblEmail;
    private JTextField VerificationCode;
    private JLabel lblWeSentAn;
    private JButton verify;
    private JLabel lblType;
    public static int code = 0;
    public int mailControl = 0;
	
    // Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAccount frame = new createAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void insert()
    {
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        conn = DriverManager.getConnection(DB_URL,USER,PASS); 
	        stmt = conn.createStatement();
	        String pass = new String(password.getPassword());
	        try {
				pass = shaControl(pass);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        sql="INSERT INTO `myaccounts` (`Username`, `Password`, `MyID`, `eMail`) VALUES ('"+username.getText()+"','"+pass+"','"+chat_username.getText()+"','"+email.getText()+"');";
	        stmt.executeUpdate(sql);
	        JOptionPane.showConfirmDialog(null, "Your account has been created!",
	                "Result", JOptionPane.DEFAULT_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
	        conn.close();
	        stmt.close();
	        this.dispose();
	    }

	    catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e1)
	    {
	        System.out.println("Exception:" + e1);
	    }}

	// Creating the Frame and objects
	public createAccount() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 513, 524);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new Panel();
		panel.setVisible(false);
		panel.setBounds(10, 236, 493, 265);
		contentPane.add(panel);
		panel.setLayout(null);
		
		VerificationCode = new JTextField();
		VerificationCode.setHorizontalAlignment(SwingConstants.CENTER);
		VerificationCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		VerificationCode.setBorder(null);
		VerificationCode.setColumns(10);
		VerificationCode.setBounds(160, 117, 157, 25);
		panel.add(VerificationCode);
		
		lblWeSentAn = new JLabel("We sent an e-mail for verification");
		lblWeSentAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeSentAn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWeSentAn.setBounds(127, 47, 219, 20);
		panel.add(lblWeSentAn);
		
		verify = new JButton("Verify");
		verify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int VCode = Integer.valueOf(VerificationCode.getText());
				if(code==VCode) {
					insert();
					Main obj = new Main();
					obj.setVisible(true);
				}else {
					JOptionPane.showConfirmDialog(null, "Verification Code is wrong!","Access Declined!", JOptionPane.DEFAULT_OPTION, 0);
				}
				
			}
		});
		verify.setFont(new Font("Tahoma", Font.BOLD, 11));
		verify.setFocusable(false);
		verify.setBackground(Color.CYAN);
		verify.setBounds(181, 162, 116, 23);
		panel.add(verify);
		
		lblType = new JLabel("Type your code in the space below ");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(127, 70, 219, 20);
		panel.add(lblType);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setIcon(new ImageIcon(createAccount.class.getResource("/confab/img/confab.png")));	//Set Icon
		jLabel1.setBounds(10, 11, 493, 214);
		contentPane.add(jLabel1);
		
		username = new JTextField();
		username.setBorder(null);
		username.setBounds(48, 267, 165, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		chat_username = new JTextField();
		chat_username.setBorder(null);
		chat_username.setColumns(10);
		chat_username.setBounds(280, 267, 165, 20);
		contentPane.add(chat_username);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(48, 236, 165, 20);
		contentPane.add(lblNewLabel);
		
		lblChatId = new JLabel("Chat Username");
		lblChatId.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChatId.setBounds(280, 236, 165, 20);
		contentPane.add(lblChatId);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(48, 298, 165, 20);
		contentPane.add(lblPassword);
		
		lblPasswordAgain = new JLabel("Re-Enter Password");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordAgain.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPasswordAgain.setBounds(280, 298, 165, 20);
		contentPane.add(lblPasswordAgain);
		
		password = new JPasswordField();
		password.setBorder(null);
		password.setBounds(48, 325, 165, 20);
		contentPane.add(password);
		
		repassword = new JPasswordField();
		repassword.setBorder(null);
		repassword.setBounds(280, 325, 165, 20);
		contentPane.add(repassword);
		
		JButton create_account = new JButton("Create Account");
		create_account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control();	// function to controlling if the user banned or not banned
			}
			
		});
		create_account.setFocusable(false);
		create_account.setBackground(new Color(0, 255, 255));
		create_account.setFont(new Font("Tahoma", Font.BOLD, 11));
		create_account.setForeground(new Color(0, 0, 0));
		create_account.setBounds(155, 433, 186, 23);
		contentPane.add(create_account);
		
		main_menu = new JButton("Back To Main Menu");
		main_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
				setVisible(false);
			}
		});
		main_menu.setFocusable(false);
		main_menu.setFont(new Font("Tahoma", Font.BOLD, 11));
		main_menu.setBackground(new Color(0, 255, 255));
		main_menu.setBounds(155, 467, 186, 23);
		contentPane.add(main_menu);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBorder(null);
		email.setBounds(165, 387, 165, 20);
		contentPane.add(email);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(165, 362, 165, 20);
		contentPane.add(lblEmail);
	}
	
	void create() { // Creating account method
		
		// Database Operations (Creating Account)
		Connection conn = null;
        Statement stmt = null;
        
       try{ 
           String us,nickname;
           int control = 0, control2 = 0, control3 = 0;
           boolean isValid = isValidEmailAddress(email.getText());
           us = username.getText(); 
           nickname = chat_username.getText(); 
           
           if(us.contains("Admin") || nickname.contains("Admin")) {
           	JOptionPane.showMessageDialog(null,"Forbidden Nickname!");
           }else {
           
           Class.forName(JDBC_DRIVER); 
           conn = DriverManager.getConnection(DB_URL,USER,PASS); 
           stmt = conn.createStatement();   
           sql="SELECT COUNT(Username) as count FROM myaccounts where Username = '"+us+"'";
           sql2="SELECT COUNT(Username) as count2 FROM myaccounts where MyId = '"+nickname+"'";
           String sql3 = "SELECT COUNT(eMail) as count3 FROM myaccounts where eMail = '"+email.getText()+"'";
           
           rs = stmt.executeQuery(sql);
           while (rs.next()) {
               control = rs.getInt("count");
             }
           
           rs2 = stmt.executeQuery(sql2);
           while (rs2.next()) {
               control2 = rs2.getInt("count2");
             }
           
           rs3 = stmt.executeQuery(sql3);
           while (rs3.next()) {
               control3 = rs3.getInt("count3");
             }
           
           if (control!=0) {
           	JOptionPane.showMessageDialog(null,"Username is already used!"); 
           }
           else if (control2!=0) {
           	JOptionPane.showMessageDialog(null,"Chat username is already used!"); 
           }
           else if (control3!=0) {
        	JOptionPane.showMessageDialog(null,"E-mail is already used!"); 
           }
           else if (isValid!=true) {
        	   JOptionPane.showMessageDialog(null,"E-mail adress is not valid!"); 
           }
           else {
           	String pass = new String(password.getPassword()); 
				String passre =  new String(repassword.getPassword());
				if(pass.equals(passre) && !pass.equalsIgnoreCase("")){
					String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.-])(?=\\S+$).{8,}";
					if(pass.matches(pattern)) {
					mailCheck();
					//insert();	// everything is correct, then create account
					//new Main().setVisible(true);
					
					panel.setVisible(true);
					}else {
						JOptionPane.showConfirmDialog(null, "Password must contain Number(0-9), Low-Uppercase - Uppercase Letter and Special Character(-*$+...)","Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					}
						
				}
				else{
					JOptionPane.showConfirmDialog(null, "Password mismatch",
							"Result", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE);
		        }
           }
       
	     rs.close(); 
	     }
       }catch(Exception er)
       {
           JOptionPane.showMessageDialog(null,"You must fill all required fields");
       } 
	}
	
	void control() {	// function to controlling if the user banned or not banned
		
		// Database Operations (controlling banned or not banned)
		Connection conn = null;
        Statement stmt = null;
        
       try{ 
           String us,nickname;
           int control = 0, control2 = 0, control3 = 0;
           us = username.getText(); 
           nickname = chat_username.getText(); 
           
           if(us.contains("Admin") || nickname.contains("Admin")) {		// prevent creating account username as admin
           	JOptionPane.showMessageDialog(null,"Forbidden Nickname!");
           }else {
           
           Class.forName(JDBC_DRIVER); 
           conn = DriverManager.getConnection(DB_URL,USER,PASS); 
           stmt = conn.createStatement();   
           sql="SELECT COUNT(Username) as count FROM banned_users where Username = '"+us+"'";
           sql2="SELECT COUNT(Username) as count2 FROM banned_users where MyId = '"+nickname+"'";
           String sql3 = "SELECT COUNT(eMail) as count3 FROM banned_users where eMail = '"+email.getText()+"'";
           
           rs = stmt.executeQuery(sql);
           while (rs.next()) {
               control = rs.getInt("count");
             }
           
           rs2 = stmt.executeQuery(sql2);
           while (rs2.next()) {
               control2 = rs2.getInt("count2");
             }
           
           rs3 = stmt.executeQuery(sql3);
           while (rs3.next()) {
               control3 = rs3.getInt("count3");
             }
           
           if (control!=0) {
           	JOptionPane.showMessageDialog(null,"Username is banned!"); 
           }
           else if (control2!=0) {
           	JOptionPane.showMessageDialog(null,"Chat username is banned!"); 
           }
           else if (control3!=0) {
        	JOptionPane.showMessageDialog(null,"E-mail is banned!"); 
           }
           else {
        	   create();	// Account creating function if not used or banned user
           }
       
	     rs.close(); 
	     }
       }catch(Exception er)
       {
           JOptionPane.showMessageDialog(null,"3");
       }
	}
	
	String shaControl(String a) throws NoSuchAlgorithmException {
		sha_256 obj = new sha_256();
		String hash = obj.toHexString(obj.getSHA(a));
		return hash;
	}
	
	void mailCheck() throws MessagingException {
		sendMail(email.getText());
		panel.setVisible(true);
	}
	
	
	
	public static void sendMail(String recepient) throws MessagingException {    	
		//System.out.println("Preparing to send");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		final String myAccountEmail = "confabpremium@gmail.com";
		final String password = "q7gutisimao";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, recepient);
		
		Transport.send(message);
		//System.out.println("Message sent");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Verification Code");
			
			Random rastgele = new Random();
	    	code = 100000 + rastgele.nextInt(900000);
	    	
			String htmlCode = "<h1> Welcome To Confab Premium!</h1> <br> <h2>Your Verification Code Is "+code+"</h2>";
			message.setContent(htmlCode, "text/html");
			//message.setText("Your Verification Code Is 123123");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isValidEmailAddress(String email) {
 	   boolean result = true;
 	   try {
 	      InternetAddress emailAddr = new InternetAddress(email);
 	      emailAddr.validate();
 	   } catch (AddressException ex) {
 	      result = false;
 	   }
 	   return result;
 	}
	
}
	

