package confab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class adminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/chatapplication";
    static final String USER = "root";
    static final String PASS = "";
    static String sql,sql2;
    static ResultSet rs,rs2;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminLogin frame = new adminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(adminLogin.class.getResource("/confab/img/icon.png")));
		setTitle("Confab - Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 513, 484);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton sign_in_1 = new JButton("Back To Login");
		sign_in_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main obj = new Main();
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
		jLabel1.setIcon(new ImageIcon(adminLogin.class.getResource("/confab/img/confab.png")));
		jLabel1.setBounds(0, 0, 500, 306);
		contentPane.add(jLabel1);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(122, 317, 122, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(254, 317, 122, 26);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBorder(null);
		username.setBounds(122, 343, 122, 20);
		contentPane.add(username);
		
		password = new JPasswordField();
		password.setBorder(null);
		password.setBounds(254, 343, 122, 20);
		contentPane.add(password);
		
		JButton sign_in = new JButton("Sign In");
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GEN-FIRST:event_button1ActionPerformed
		        // TODO add your handling code here:
		         Connection conn = null;
		         Statement stmt = null;
		         
		        try{ 
		            String us,pas,admin_username=null; 
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
		            
		            sql="SELECT Count(Username) as count FROM admin where Username = '"+us+"' and Password = '"+pas+"'";   
		            sql2="SELECT Name FROM admin where Username = '"+us+"' and Password = '"+pas+"'";   
		            
		            rs2 = stmt.executeQuery(sql2);

		            while (rs2.next()) {
		                admin_username=rs2.getString("Name");
		              }   	            
		            
		            
		            rs = stmt.executeQuery(sql);
		             
		            int count = 0;
		            while (rs.next()) {
		                count=rs.getInt("count");
		              }   
		            
		            
		            
		            if(count == 1){ 
		            
		                JOptionPane.showMessageDialog(null,"Access Granted!");  
		                 //nicknamethrow 
		                //new chatScreen(us,pas,"Admin "+admin_username,"Absorb").setVisible(true); 
		                chatScreen obj = new chatScreen(us,pas,"Admin "+admin_username);
		                obj.setVisible(true);
		                obj.controlPanel.setVisible(true);
		                setVisible(false);
		            }
		            else
		            {
		                if(us.equals("") && pas.equals("")){
		                     JOptionPane.showMessageDialog(null,"Username or password cannot be left blank! \nAccess Declined!");
		                     
		                }
		                else{
		                    JOptionPane.showMessageDialog(null,"Account not found!"); 
		                }
		                
		            }
			     rs.close(); 
		        }catch(Exception er)
		        {
		            JOptionPane.showMessageDialog(null,"2");
		        } 
		        
			}
		});
		sign_in.setFont(new Font("Tahoma", Font.BOLD, 11));
		sign_in.setFocusable(false);
		sign_in.setBackground(Color.CYAN);
		sign_in.setBounds(174, 374, 160, 23);
		contentPane.add(sign_in);
	}
	
	String shaControl(String a) throws NoSuchAlgorithmException {
		sha_256 obj = new sha_256();
		String hash = obj.toHexString(obj.getSHA(a));
		return hash;
	}
	
}
