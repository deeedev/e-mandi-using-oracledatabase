package utility;

import java.util.Map;
import java.util.TreeMap;

import model.Crops;

public interface Trade {

	public static Map<String, Crops> buyOrder = new TreeMap<String, Crops>();
	public static Map<String, Crops> sellOrderWholeSeller = new TreeMap<String, Crops>();
	public static Map<String, Crops> sellOrderFarmer = new TreeMap<String, Crops>();

	public void Buy();

	public void Sell();

}
