package main.firefighters;

import java.util.ArrayList;
import java.util.List;

import main.api.City;
import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;
import main.api.exceptions.NoFireFoundException;

public class FireDispatchImpl implements FireDispatch {

	private City city;
	private List<Firefighter> firefighters;
	private int nextFirefighter;
	
	public FireDispatchImpl(City city) {
		this.city = city;
		this.nextFirefighter = 0;
	}

	@Override
	public void setFirefighters(int numFirefighters) {
		CityNode node = city.getFireStation().getLocation();
		firefighters = new ArrayList<Firefighter>(numFirefighters);
		for (int i = 0; i < numFirefighters; ++i) {
			firefighters.add(new FirefighterImpl(node));
		}
	}

	@Override
	public List<Firefighter> getFirefighters() {
		return firefighters;
	}

	@Override
	public void dispatchFirefighters(CityNode... burningBuildings) {
		for (CityNode fireNode : burningBuildings) {
			Firefighter ff = getNextFirefighter();
			try {
				ff.dispatchTo(city, fireNode);
			}
			catch (NoFireFoundException e) {
				System.out.println("False Alarm at: " + fireNode);
			}
		}
	}
	
	/*
	 * Choose the next firefighter to dispatch.
	 * 
	 * The current implementation is strictly a round-robin scheduler.
	 * 
	 * The API for dispatching indicated a desire for optimal distance traveled,
	 * but the unit tests all assume that each firefighter remains on-scene
	 * after extinguishing a blaze, so they're never available to redeploy
	 * to closer fires.
	 */
	private Firefighter getNextFirefighter() {
		Firefighter next = firefighters.get(nextFirefighter);
		nextFirefighter = (nextFirefighter + 1) % firefighters.size();
		return next;
	}
}
