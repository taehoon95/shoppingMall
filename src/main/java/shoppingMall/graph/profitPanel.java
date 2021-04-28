package shoppingMall.graph;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;

@SuppressWarnings("serial")
public class profitPanel extends JPanel{
	public profitPanel() {
	}

	private saleService sService;
	private productService pService;
	private List<Sale> sList;

	public JFreeChart getChart() {

		sService = new saleService();
		pService = new productService();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		List<Product> pList = pService.showProd();
		for (Product p : pList) {
			sList = sService.selectProductByProInfo(p);
			int totalProfit = sList.parallelStream().mapToInt(Sale::getProfit).sum();
			dataset.setValue(totalProfit,"제품", p.getProname());
		}

		// 렌더링 생성

		final BarRenderer renderer = new BarRenderer();

		// 공통 옵션 정의
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(
			ItemLabelAnchor.CENTER, TextAnchor.CENTER
		);

		Font f = new Font("Gulim", Font.BOLD, 11);
		Font axisF = new Font("Gulim", Font.PLAIN, 15);

		// 그래프 1
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(p_center);
		renderer.setBaseItemLabelFont(f);
		renderer.setSeriesPaint(0, new Color(0, 162, 255));

		// plot 생성
		final CategoryPlot plot = new CategoryPlot();

		// plot 에 데이터 적재
		plot.setDataset(dataset);
		plot.setRenderer(renderer);
		
		// plot 기본 설정
		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X축 세팅
		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

		// Y축 세팅
		plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

		final JFreeChart chart = new JFreeChart(plot);

		return chart;
	}

}
