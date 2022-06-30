package confab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;

public class Main extends JFrame {

	// Declaring Global Variables
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/chatapplication";
    static final String USER = "root";
    static final String PASS = "";
    static String sql;
    static ResultSet rs;
    static ResultSet rs1;
    static String nicknamethrow;
    static String val1,val2;

	
	// Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creating the Frame and objects
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\burak\\eclipse-workspace\\confab\\src\\confab\\img\\icon.png"));	// Icon
		setTitle("Confab");																												// Title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 513, 484);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton sign_in_1 = new JButton("Admin Login");
		sign_in_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminLogin obj = new adminLogin();				// Creating an object from admin login frame
				obj.setVisible(true);
				setVisible(false);
			}
		});
		sign_in_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		sign_in_1.setFocusable(false);
		sign_in_1.setBackground(Color.CYAN);
		sign_in_1.setBounds(370, 11, 117, 23);
		contentPane.add(sign_in_1);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setIcon(new ImageIcon(Main.class.getResource("/confab/img/confab.png")));
		
		jLabel1.setBounds(0, 0, 500, 306);
		contentPane.add(jLabel1);
		
		username = new JTextField();
		username.setBorder(null);
		username.setBounds(122, 343, 122, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(122, 317, 122, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(254, 317, 122, 26);
		contentPane.add(lblPassword);
		
		JButton sign_in = new JButton("Sign In");
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Database Operations (Signing In)
		        Connection conn = null;
		        Statement stmt = null;
		         
		        try{ 
		            String us,pas; 
		            us = username.getText(); 
		            pas = new String(password.getPassword());
		            
		            try {
						pas = shaControl(pas);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		            
		            Class.forName(JDBC_DRIVER); 
		            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		            stmt = conn.createStatement();   
		            sql="SELECT * FROM myaccounts where Username = '"+us+"' and Password = '"+pas+"'";   // account query
		            rs = stmt.executeQuery(sql);
		             
		            int count = 0;
		            while (rs.next()) {
		                nicknamethrow=rs.getString("MyID");
		                count++;
		              }   

		            if(count == 1){ // if username and password match, login is done
		                JOptionPane.showMessageDialog(null,"Access Granted!");  
		                chatScreen obj = new chatScreen(us,pas,nicknamethrow);
		                obj.setVisible(true);
		                setVisible(false);
		            }
		            else
		            {
		                if(us.equals("") && pas.equals("")){	// if username or password is blank, login is not done
		                     JOptionPane.showMessageDialog(null,"Username or password cannot be left blank! \nAccess Declined!");
		                     
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"Account not found!"); 	// if username or password does not match, login is not done
		                }
		                
		            }
			     rs.close(); 
		        }catch(Exception er)
		        {
		            JOptionPane.showMessageDialog(null,"2");
		        } 
		        
			}
		});
		sign_in.setFocusable(false);
		sign_in.setBackground(new Color(0, 255, 255));
		sign_in.setFont(new Font("Tahoma", Font.BOLD, 11));
		sign_in.setBounds(174, 374, 160, 23);
		contentPane.add(sign_in);
		
		JButton create_account = new JButton("Create Account");
		create_account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new createAccount().setVisible(true);
				setVisible(false);
			}
		});
		create_account.setFocusable(false);
		create_account.setBackground(new Color(0, 255, 255));
		create_account.setFont(new Font("Tahoma", Font.BOLD, 11));
		create_account.setBounds(174, 408, 160, 23);
		contentPane.add(create_account);
		
		password = new JPasswordField();
		password.setBorder(null);
		password.setBounds(254, 343, 122, 20);
		contentPane.add(password);
	}
	
	String shaControl(String a) throws NoSuchAlgorithmException {
		sha_256 obj = new sha_256();
		String hash = obj.toHexString(obj.getSHA(a));
		return hash;
	}
}
