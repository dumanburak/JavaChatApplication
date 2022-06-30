package confab;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ekle extends JFrame {

	
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
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekle frame = new ekle();
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
	public ekle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
			    Statement stmt = null;
				try {
			        

			        for (int i = 0; i < 500000; i++) {
			        	Class.forName("com.mysql.jdbc.Driver").newInstance();
				        conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				        stmt = conn.createStatement();
				        String sql="INSERT INTO `myaccounts` (`Username`, `Password`, `MyID`, `eMail`) VALUES ('birol','can','123','123');";
				        stmt.executeUpdate(sql);
				        
				        conn.close();
				        stmt.close();
					}
			        
			    }

			    catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e1)
			    {
			        System.out.println("Exception:" + e1);
			    }
			}
		});
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}

}
