package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Conexion;
import model.User;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Principal  extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	Connection miconexion = new Conexion().conectar();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
		initialize();
		verUsuarios();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
getContentPane().setLayout(null);
		
		JLabel lblEstasDetroEnhorabuena = new JLabel("eres un  jakier");
		lblEstasDetroEnhorabuena.setBounds(10, 11, 79, 14);
		getContentPane().add(lblEstasDetroEnhorabuena);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 50));
		panel.setBounds(20, 33, 289, 217);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"id", "usuario", "password", "estado"},
			},
			new String[] {
				"id", "usuario", "password", "estado"
			}
		));
		table.setBounds(10, 30, 269, 176);
		panel.add(table);
		
		JLabel lblListadoDeUsuarios = new JLabel("Listado de usuarios");
		lblListadoDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListadoDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeUsuarios.setBounds(23, 11, 225, 14);
		panel.add(lblListadoDeUsuarios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setBounds(333, 33, 205, 217);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 84, 68, 14);
		panel_1.add(lblContrasea);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 152, 46, 14);
		panel_1.add(lblEstado);
		
		textField = new JTextField();
		textField.setBounds(84, 8, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(84, 81, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 149, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(153, 7, 89, 23);
		getContentPane().add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(271, 7, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(393, 7, 89, 23);
		getContentPane().add(btnActualizar);
		
	}
	
	public ArrayList<User> listadousuarios() {
		ArrayList<User> usuarios = new ArrayList<User>();
		
		try {
			Statement st = miconexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user");
			
			User usuario;
			while(rs.next()) {
				usuario = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				usuarios.add(usuario);
			}//cierra el while
			
			st.close();
			rs.close();
			miconexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuarios;
	}//cierra listado usuarios
	
	private void verUsuarios() {//carga un modelo con los objetos del array list
		System.out.println("Llamada a ver usuartio");
		ArrayList<User> usuariosparatabla = listadousuarios();
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		Object fila[] = new Object[4];
		for (int i = 0; i < usuariosparatabla.size(); i++) {
			fila[0] = usuariosparatabla.get(i).getId();
			fila[1] = usuariosparatabla.get(i).getUsername();
			fila[2] = usuariosparatabla.get(i).getPassword();
			fila[3] = usuariosparatabla.get(i).getUser_status();
			
			modelo.addRow(fila);
		}
	}
}
