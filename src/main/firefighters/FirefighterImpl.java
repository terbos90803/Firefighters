package main.firefighters;

import main.api.Building;
import main.api.City;
import main.api.CityNode;
import main.api.Firefighter;
import main.api.exceptions.NoFireFoundException;

public class FirefighterImpl implements Firefighter {
	private CityNode node; // current location
	private int distance; // total distance traveled

	public FirefighterImpl(CityNode node) {
		this.node = node;
		this.distance = 0;
	}

	@Override
	public CityNode getLocation() {
		return node;
	}

	@Override
	public int distanceTraveled() {
		return distance;
	}

	// Travel to a fire and extinguish it
	@Override
	public void dispatchTo(City city, CityNode node) throws NoFireFoundException {
		// calculate travel distance
		final int newX = node.getX();
		final int newY = node.getY();
		final int diffX = Math.abs(newX - this.node.getX());
		final int diffY = Math.abs(newY - this.node.getY());
		distance += diffX + diffY; // Add Manhattan distance traveled
		
		// Relocate firefighter to new location
		this.node = node;
		
		// Extinguish fire
		final Building building = city.getBuilding(node);
		building.extinguishFire();
	}
}
