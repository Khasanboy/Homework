package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import controller.Controller;
import model.FuelData;

public class Tests {
	
	 @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	 
	 private Controller controller = new Controller("C:\\Users\\tsar\\Desktop\\Homework\\data\\data.txt");
	

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
		System.out.println(controller.getResult().getMonths()[0].getTotalValue());
		Assert.assertEquals((long)252.931, controller.getResult().getMonths()[0].getTotalValue().intValue());
	}
	

}
