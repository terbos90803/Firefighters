package main.api;

import main.api.exceptions.NoFireFoundException;

public interface Firefighter {

	/**
	 * Get the firefighter's current location. Initially, the firefighter should be
	 * at the FireStation
	 *
	 * @return {@link CityNode} representing the firefighter's current location
	 */
	CityNode getLocation();

	/**
	 * Get the total distance traveled by this firefighter. Distances should be
	 * represented using TaxiCab Geometry:
	 * https://en.wikipedia.org/wiki/Taxicab_geometry
	 *
	 * @return the total distance traveled by this firefighter
	 */
	int distanceTraveled();

	/**
	 * Dispatch the firefighter to a burning building.
	 * 
	 * @param city The city
	 * @param node The CityNode containing the burning building
	 */
	void dispatchTo(City city, CityNode node) throws NoFireFoundException;
}
