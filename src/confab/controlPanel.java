package confab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class controlPanel extends JFrame {
	
	// Declaring Global Variables
	static String timer;
    static String val1,val2,val3; 
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/chatapplication";
    static final String USER = "root";
    static final String PASS = "";
    static String sql,sql2,sql3,sql4,sql5,sql6;
    static ResultSet rs,rs2,rs3,rs4,rs7;
    static ResultSet rs1;
    static String t1,t2,t3;
    static String allstring;
    String email=null;
    String user;
    
    String username;
    String chat_username;
    
    private JTextArea allchat;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel myModel = new DefaultTableModel();
	
	Object[] columns = {"Username","Chat Username"};
	Object[] rows = new Object[2];
	private JTextField username_text;
	private JLabel lblSearch;
	private JTextField textField;
	private JTextField mail;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controlPanel frame = new controlPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creating the Frame and objects
	public controlPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(controlPanel.class.getResource("/confab/img/icon.png")));	// Icon
		setTitle("Control Panel");																					// Title
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(713, 200, 451, 455);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 414, 134);
		contentPane.add(scrollPane);
		
		allchat = new JTextArea();
		allchat.setEditable(false);
		allchat.setBounds(10, 147, 414, 16);
		//contentPane.add(textArea);
		scrollPane.setViewportView(allchat);
		
		JLabel lblNewLabel = new JLabel("Chat Log");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(168, 11, 92, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 200, 414, 100);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// when clicked table row, assigned values to variables
				username = (String) myModel.getValueAt(table.getSelectedRow(), 0);			
				chat_username = (String) myModel.getValueAt(table.getSelectedRow(), 1);
			}
		});
		
		myModel.setColumnIdentifiers(columns);
		
		table.setBounds(34, 179, 1, 1);
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton = new JButton("Select User");
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Selecting user 
				username = (String) myModel.getValueAt(table.getSelectedRow(), 0);
				chat_username = (String) myModel.getValueAt(table.getSelectedRow(), 1);
				textField.setText(chat_username + " selected!");

				Connection conn = null;
		        Statement stmt = null;
		        try{
		            Class.forName(JDBC_DRIVER); 
		            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		            stmt = conn.createStatement();   
		            String sql7 = "SELECT eMail FROM myaccounts WHERE Username='"+username+"'";   
		            rs = stmt.executeQuery(sql7);
		            
		            String dene=null;
		            while (rs.next()) {
		                 dene = rs.getString("eMail");
		                 mail.setText(dene);
		                 
		              }   
			     rs.close();  
		         conn.close();
		        }catch(Exception er) {
		        	er.printStackTrace();
		        }
				
			}
		});
		btnNewButton.setBounds(164, 330, 107, 23);
		contentPane.add(btnNewButton);
		
		username_text = new JTextField();
		username_text.setBorder(null);
		username_text.setToolTipText("");
		username_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 			// word by word search method
				myModel.setRowCount(0);
				
				// Database Operations(Word by Word Search)
				Connection conn = null;
		        Statement stmt = null;
		        
		        String uname = username_text.getText() ;

		        try{
		            Class.forName(JDBC_DRIVER); 
		            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		            stmt = conn.createStatement();
		            sql3="SELECT Username,MyID FROM myaccounts where MyID like'"+ uname +"%'";   
		            rs3 = stmt.executeQuery(sql3);
		            int i = 0;
		            while (rs3.next()) {
		            	rows[0] = rs3.getString("Username");
		            	rows[1] = rs3.getString("MyID");
		            	myModel.addRow(rows);
		            	i++;
		            	System.out.print(i+ " ");
		            	System.out.println(rows[0]);
		              }   

			     rs3.close();  
		             conn.close();
		        }catch(Exception er) {
		        	er.printStackTrace();
		        }
		        table.setModel(myModel);
			
			
				
				
			}
		});
		username_text.setBounds(10, 330, 144, 23);
		contentPane.add(username_text);
		username_text.setColumns(10);
		
		lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearch.setBounds(33, 311, 92, 14);
		contentPane.add(lblSearch);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setEditable(false);
		textField.setToolTipText("");
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(277, 330, 144, 23);
		contentPane.add(textField);
		
		JButton btnNewButton_1 = new JButton("BAN USER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Database Operations (Banning User)
				Connection conn = null;
		        Statement stmt = null;

		        try{
		            Class.forName(JDBC_DRIVER); 
		            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		            stmt = conn.createStatement();
		            sql4="DELETE FROM myaccounts WHERE MyID='"+chat_username+"'";
			        stmt.executeUpdate(sql4);
		           
			        conn.close();
			        stmt.close();
			        JOptionPane.showMessageDialog(null,chat_username+" has been banned!");
			        
			        textField.setText("");
			        
			        listUsers();	// function to list users in tabular form
			        
			       // getEmail(username);
			        
			        banUser(username,chat_username,mail.getText()); // function to ban users
			        
			        
		        }catch(Exception er) {
		        	er.printStackTrace();
		        }			
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(164, 382, 107, 23);
		contentPane.add(btnNewButton_1);
		
		mail = new JTextField();
		mail.setVisible(false);
		mail.setBounds(277, 331, 116, 20);
		contentPane.add(mail);
		mail.setColumns(10);
		
		javax.swing.Timer t = new javax.swing.Timer(1000,new ActionListener(){@Override			// Refreshing chat per second
	         public void actionPerformed(ActionEvent e){
	   				Calendar now = Calendar.getInstance();
	                int h = now.get(Calendar.HOUR_OF_DAY);
	                int m = now.get(Calendar.MINUTE);
	                int s = now.get(Calendar.SECOND);
	                timer = " " + h + ":" + m + ":" + s + " ";    
	                setTitle("Welcome to Chat - "+val1);
	                
	                writeLog();  // function to printing bad word log
	            }
	        });
	        t.start();
		
	        listUsers();	// function to list users in tabular form
	}
	
	void listUsers() {	// function to list users in tabular form

		// Database Operations (Listing Users)
		myModel.setRowCount(0);
		Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();   
            sql2="SELECT Username,MyID FROM myaccounts";   
            rs2 = stmt.executeQuery(sql2);

            while (rs2.next()) {
            	rows[0] = rs2.getString("Username");
            	rows[1] = rs2.getString("MyID");
            	myModel.addRow(rows);
              }   
           
	     rs2.close();  
             conn.close();
        }catch(Exception er) {
        	er.printStackTrace();
        }
        table.setModel(myModel);
	
	}
	
	
	void writeLog(){	// function to printing bad word log
		
		// Database Operations (Printing Bad Word Log)
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();   
            sql="SELECT * FROM badwordlog";   
            rs = stmt.executeQuery(sql);
            
            allchat.setText("");
            while (rs.next()) {
                  
                 t1=rs.getString("Sender");
                 t2=rs.getString("Chat");
                 t3=rs.getString("Time");
                 allstring ="\n "+t1+" ["+t3+"] \n >>"+t2+"\n";
                 allstring = allchat.getText()+allstring  ;
                 allchat.setText("");
                 allchat.setText(allstring); 
                
                 
              }   
           
	     rs.close();  
             conn.close();
        }catch(Exception er) {
        	er.printStackTrace();
        }
        
    
	
	}
	
	void banUser(String username, String chat_username, String mmail) {	// function to ban users
		try{
			
			// Database Operations (Banning User)
			Connection conn = null;
	        Statement stmt = null;
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();
            
            
            sql5="INSERT INTO `banned_users` (`Username`, `MyID`, `eMail`) VALUES ('"+username+"','"+chat_username+"','"+mmail+"')";
            
	        stmt.executeUpdate(sql5);
           
	        conn.close();
	        stmt.close();
	        
	        notification(chat_username);	// function that prints banned user in chat log
	        
        }catch(Exception er) {
        	er.printStackTrace();
        }	
	}
	
	void notification(String chat_username) {	// function that prints banned user in chat log
		try{
			
			// Database Operations (Printing Banned User)
			String Time = timer;
			Connection conn = null;
	        Statement stmt = null;
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();
            sql6="INSERT INTO `mychat` (`Sender`, `Chat`, `Time`, `BadWords`) VALUES ('Admin','"+chat_username+" has been banned because of badwords "+"','"+Time+"','0')";
	        stmt.executeUpdate(sql6);
           
	        conn.close();
	        stmt.close();	        
        }catch(Exception er) {
        	er.printStackTrace();
        }
	
	}
	
	void getEmail(String username) {
		System.out.println(username);
		Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();   
            String sql7 = "SELECT eMail FROM myaccounts WHERE Username='"+username+"'";   
            rs = stmt.executeQuery(sql7);
            
            while (rs.next()) {
                 System.out.println(rs.getString("eMail"));
                 mail.setText(email);
                 
                 
              }   
           
	     rs.close();  
         conn.close();
        }catch(Exception er) {
        	er.printStackTrace();
        }
	}
}
