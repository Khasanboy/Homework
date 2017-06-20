package tests;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import controller.Controller;
import model.FuelData;
import model.Result;

public class Tests {
	
	 @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	 

	 private Controller controller = new Controller("C:\\Users\\GANALOGICS10\\workspace\\Homework\\test\\test.txt");
	

	@Test
	public void testAllDataSizeForFileLines() {
		Assert.assertEquals(20, controller.getAllData().size());
	}
	
	@Test
	public void testFilterDateWithGasolineName(){
		ArrayList<FuelData> filteredList = controller.FilterDataWithGasolineName(controller.getAllData(), "95");
		Assert.assertEquals(4, filteredList.size());
	}
	
	@Test
	public void testAllDataJanuarysValue(){
		controller.finalData(controller.getAllData(), "All");
		Assert.assertEquals(0, new BigDecimal("252.9314400000000091495166998356580734252929687500").compareTo(controller.getResult().getMonths()[0].getTotalValue()));
	}
	
	@Test
	public void testMakeResult(){
		ArrayList<FuelData> filteredList = controller.FilterDataWithGasolineName(controller.getAllData(), "95");
		Result result = controller.makeResult(filteredList);
		
		Assert.assertTrue(result.getMonths().length == 12);
		Assert.assertEquals(result.getMonths()[0].getId(), 0);
		
	}
	

}
