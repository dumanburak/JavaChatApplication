package confab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class chatScreen extends JFrame {

	// Declaring Global Variables
	private JPanel contentPane;
	
	static String timer;
    static String val1,val2,val3; 
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/chatapplication";
    static final String USER = "root";
    static final String PASS = "";
    static String sql,sql2,sql3;
    static ResultSet rs,rs2;
    static ResultSet rs1;
    static String t1,t2,t3;
    static String allstring;
    static String user,passy;
    private JTextArea allchat;
    private JTextArea chatBox;
    private JScrollPane scrollPane_1;
    public JButton controlPanel;
    ArrayList<String> words = new ArrayList<String>();	// we create arraylist to query and assign banned words

    // Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//chatScreen frame = new chatScreen();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creating the Frame and objects
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 513, 484);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBackground(new Color(255, 248, 220));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sendMessage();	// function to send message
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(388, 366, 99, 54);
		contentPane.add(btnNewButton);
		
		allchat = new JTextArea();
		allchat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		allchat.setEditable(false);
		allchat.setBounds(150, 59, 130, 69);
		//contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 477, 180);
		scrollPane.setViewportView(allchat);
		contentPane.add(scrollPane);
		
		chatBox = new JTextArea();
		chatBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chatBox.setBounds(63, 289, 158, 23);
		//contentPane.add(textArea_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 355, 368, 79);
		scrollPane_1.setViewportView(chatBox);
		contentPane.add(scrollPane_1);
		
		controlPanel = new JButton("Control Panel");
		controlPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controlPanel obj = new controlPanel();	// Creating an object from control panel frame
					obj.setVisible(true);
			}
		});
		controlPanel.setVisible(false);
		controlPanel.setFont(new Font("Tahoma", Font.BOLD, 11));
		controlPanel.setFocusable(false);
		controlPanel.setBackground(Color.CYAN);
		controlPanel.setBounds(370, 11, 117, 23);
		contentPane.add(controlPanel);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setIcon(new ImageIcon(chatScreen.class.getResource("/confab/img/confabwithoutpremium.png")));	// Set Icon
		jLabel1.setBounds(0, 0, 500, 149);
		contentPane.add(jLabel1);
		

	}
	
	public chatScreen(String us,String pas,String nickname) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\burak\\eclipse-workspace\\confab\\src\\confab\\img\\icon.png"));	// Set Icon

        initComponents();
        user = us; passy=pas;
        val1 = nickname;
                
         javax.swing.Timer t = new javax.swing.Timer(1000,new ActionListener(){@Override
         public void actionPerformed(ActionEvent e){
   				Calendar now = Calendar.getInstance();
                int h = now.get(Calendar.HOUR_OF_DAY);
                int m = now.get(Calendar.MINUTE);
                int s = now.get(Calendar.SECOND);
                timer = " " + h + ":" + m + ":" + s + " ";    
                setTitle("Welcome to Chat - "+val1);

                    findchat();  // function to print all chat table
            }
        });
        t.start();
        bannedControl();
                
    
        
	}

	
	private void findchat() {	// Printing all chat table method
		// Database Operations(Printing all chat table)
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            stmt = conn.createStatement();   
            sql="SELECT * FROM mychat";   
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
	
	void sendMessage() throws ClassNotFoundException {	// Sending message method

		// Database Operations(Sending message)
        Connection conn = null;
        Statement stmt = null;
        
    try {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String Time = timer;
        stmt = conn.createStatement();   
        sql2="SELECT Word FROM forbidden_words";   
        rs2 = stmt.executeQuery(sql2);
         
        while (rs2.next()) {
            words.add(rs2.getString("Word"));		// quering and assigning banned words to arraylist named words
          }   
        
        String Sender,Chat="";
        int BadWords=0;
        Sender = val1;
        Chat = chatBox.getText();
        String UnFilteredChat = chatBox.getText();
        int control = 2 ;
        
        for (int i = 0; i < words.size(); i++) {
			if(Chat.contains(words.get(i))==true){	// checking whether bad words are used in the sentence.
				Chat="*****";						// filtering message
				BadWords = 1;
				stmt = conn.createStatement();   
		        sql3="INSERT INTO `badwordlog` (`Sender`, `Chat`, `Time`) VALUES ('"+Sender+"','"+UnFilteredChat+"','"+Time+"')";	// adding row to badwordlog table
		        
		        int input = JOptionPane.showConfirmDialog(null, "This message looks suspicious, are you sure to send?", "Warning!", JOptionPane.YES_NO_OPTION, 2);
		        if(input==0) {
		        	stmt.executeUpdate(sql3);
		        	control = 2;
		        }else {
		        	control = 0;
		        }
			}
		}        
        
        if (control==2) {
        
        sql="INSERT INTO `mychat` (`Sender`, `Chat`, `Time`, `BadWords`) VALUES ('"+Sender+"','"+Chat+"','"+Time+"','"+BadWords+"')";	// adding row to mychat table
        stmt.executeUpdate(sql);
        JOptionPane.showConfirmDialog(null, "Message Sent!",
                "Result", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        
        allchat.setText(Chat);
        
        conn.close();
        stmt.close(); 
        chatBox.setText(""); 
    } }

    catch (SQLException e1)
    {
        System.out.println("Exception:" + e1);
    }
    
	}
	
	
	private static void bannedControl() {
			final Timer myTimer=new Timer();
	        TimerTask gorev =new TimerTask() {

	               @Override
	               public void run() {
	            	   try {
	            		 Connection conn = null;
	  	  		         Statement stmt = null;
	  	            	 Class.forName(JDBC_DRIVER); 
	  			         conn = DriverManager.getConnection(DB_URL,USER,PASS); 
	  			         stmt = conn.createStatement();
	  			            
	  			         sql="SELECT Count(Username) as count FROM myaccounts where Username = '"+user+"' and Password = '"+passy+"'";
	  			         rs = stmt.executeQuery(sql);
	  			             
	  			         int count = 0;
	  			         while (rs.next()) {
	  			             count=rs.getInt("count");
	  			           }
	  			         
	  			         
	  			         if(!val1.contains("Admin")) {
	  			        	if(count==0) {
		  			        	JOptionPane.showConfirmDialog(null, "You Were Banned!", "!!", JOptionPane.DEFAULT_OPTION, 0);
		  			        	System.exit(0);
		  			         }
	  			         }
	  			         
	  			         
	  			         
					} catch (Exception e) {
						// TODO: handle exception
					}
	            
	            	 
	               }
	        };

	        myTimer.schedule(gorev,0,5000);
		}
	
}
