package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import conexao.Conexao;

public class Irregulares extends JFrame {
    PreparedStatement pstm;
	ResultSet rs;
	static Connection conn = new Conexao().getConexao();
	String sql = "Select razao_social, count(servico_regular) as Qtd\r\n" + "from bd.veiculos where servico_regular like 'Não'\r\n"
			+ "group by razao_social\r\n" + "having count(razao_social) > 1\r\n"
			+ "order by count(razao_social) desc "
			+ "limit 10;";


    public  Irregulares() throws SQLException {

		JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, sql);
		JFreeChart chart = ChartFactory.createBarChart3D("10 Empresas com mais Servicos Irregulares em 2022", "Empresas", "Quantidade", dataset,
				PlotOrientation.HORIZONTAL, true, true, true);
				final CategoryPlot plot = chart.getCategoryPlot();
				final CategoryAxis axis = plot.getDomainAxis();
				axis.setCategoryLabelPositions(
					CategoryLabelPositions.createUpRotationLabelPositions(0.10)
				);
				
				final CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setItemLabelsVisible(true);
				final BarRenderer r = (BarRenderer) renderer;
				r.setMaximumBarWidth(0.05);
        this.add(new ChartPanel(chart));
        this.setVisible(true);
        this.pack();
		
		
		
		conn.close();

	}


  
}
