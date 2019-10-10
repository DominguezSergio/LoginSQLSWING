package model;

public class User {
	private int id;
	private String username, password, user_status;
	
	public User(int codigo, String nombre, String contrasena, String estado) {
		this.id = codigo;
		this.username = nombre;
		this.password = contrasena;
		this.user_status = estado;
	}//Cierra constructor

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUser_status() {
		return user_status;
	}
	
	//Sobrecargar toString
	
}//Cierra e clsass
