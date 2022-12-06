package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String driver = "org.postgresql.Driver";
	private String url;
	private String usuario;
	private String senha;
	private Connection con;

	public Conexao() {
		url = "jdbc:postgresql://localhost:5432/Tarefa";
		usuario = "postgres";
		senha = "1234";

		try {
			Class.forName(driver);

			this.con = (Connection) DriverManager.getConnection(url, usuario, senha);
			System.out.println("ok");
			
		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
	}

	public Connection getConexao() {
		return this.con;
	}

	public void fecharConexao() {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
