package conexao.principal;

import java.sql.SQLException;

import view.Irregulares;
import view.MaisVeiculos;
import view.Regulares;

public class Principal {
    public static void main(String[] args) throws SQLException {
		new MaisVeiculos().setVisible(true);
		new Irregulares().setVisible(true);
		new Regulares().setVisible(true);	
	}
}
