package model;

import java.util.ArrayList;
import java.util.List;

public class WorldConfig {
	World world;
	List<String> categories;
	List<Double> densities;
	
	public WorldConfig(World world, ArrayList<String> categories, ArrayList<Double> densities) {
		this.world=  world;
		this.categories = categories;
		this.densities = densities;
	}
	
	public World getWorld() {
		return world;
	}
	
	public List<String> getCategories(){
		return categories;
	}
	
	public List<Double> getDensities(){
		return densities;
	}
}
