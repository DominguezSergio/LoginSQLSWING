package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import controller.Conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JTextField txtnombre;
	private JTextField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(51, 34, 46, 14);
		frame.getContentPane().add(lblnombre);
		
		JLabel lblpassword = new JLabel("Contrase\u00F1a");
		lblpassword.setBounds(51, 85, 89, 14);
		frame.getContentPane().add(lblpassword);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(192, 31, 86, 20);
		frame.getContentPane().add(txtnombre);
		txtnombre.setColumns(10);
		
		txtpass = new JTextField();
		txtpass.setBounds(192, 82, 86, 20);
		frame.getContentPane().add(txtpass);
		txtpass.setColumns(10);
		
		JButton btnreset = new JButton("Reset");
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Boton reset");
				txtnombre.setText("");
				txtpass.setText("");
			}
		});
		btnreset.setBounds(51, 166, 89, 23);
		frame.getContentPane().add(btnreset);
		
		JButton btnenviar = new JButton("Login");
		btnenviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Boton login");
				acceder();
			}
		});
		btnenviar.setBounds(224, 166, 89, 23);
		frame.getContentPane().add(btnenviar);
	}
	
	private void acceder() {
		System.out.println("metodo acceder");
		Connection miconexion = new Conexion().conectar();
		String consulta = "SELECT * FROM user where username=? AND password=?";

		try {
			PreparedStatement ps = miconexion.prepareStatement(consulta);
			ps.setString(1, txtnombre.getText());
			ps.setString(2, txtpass.getText());
			
			ResultSet rs = ps.executeQuery();
			
			//System.out.println(rs.next());
			
			if (rs.next()) {
				System.out.println("Estoy dentro");
				Principal p = new Principal();
				p.setVisible(true);// .show(esta depreciada)
			} else {
				JOptionPane.showMessageDialog(null, "Login no valido");
			}
			ps.close();
			rs.close();
			miconexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
}//cierra el clss
