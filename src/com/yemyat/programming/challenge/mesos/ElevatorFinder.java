package com.yemyat.programming.challenge.mesos;

import java.util.Map;

import com.yemyat.programming.challenge.mesos.interfaces.IElevator;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorFinder;

public class ElevatorFinder implements IElevatorFinder {

	@Override
	public int getNextElevatorId(Map<Integer, IElevator> elevators,
			int goalFloor, int direction) {
		int load = 1000;
		IElevator e = null;
		for (Integer elvId : elevators.keySet()) {
			IElevator elv = elevators.get(elvId);
			if (elv.getCurrentLoad() < load) {
				load = elv.getCurrentLoad();
				e = elv;
			}
		}
		return e.getElevatorId();
	}

}
