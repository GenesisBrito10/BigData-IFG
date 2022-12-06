
package view;

import java.awt.Font;

import java.awt.Stroke;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;

import conexao.Conexao;

public class MaisVeiculos extends JFrame {
	PreparedStatement pstm;
	ResultSet rs;
	static Connection conn = new Conexao().getConexao();
	String sql = "Select razao_social, count(razao_social) as Qtd\r\n" + "from bd.veiculos \r\n"
			+ "group by razao_social\r\n" + "having count(razao_social) > 1\r\n"
			+ "order by count(razao_social) desc "
			+ "limit 10;";

	public MaisVeiculos() throws SQLException {

		DefaultPieDataset grafico = new DefaultPieDataset();

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("razao_social");
				int qtd = rs.getInt("qtd");
				grafico.setValue(nome, qtd);

			}
			JFreeChart PieChartObject = ChartFactory
					.createPieChart("As 10 Empresas com mais Veiculos Habilitados em 2022", grafico, true, true, false);
			rs.close();
			pstm.close();
			conn.close();

			this.add(new ChartPanel(PieChartObject));

			this.setVisible(true);
			this.pack();

		} catch (Exception e) {

		}

	}

	

}
