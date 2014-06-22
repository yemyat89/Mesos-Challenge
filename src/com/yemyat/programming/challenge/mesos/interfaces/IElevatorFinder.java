package com.yemyat.programming.challenge.mesos.interfaces;

import java.util.Map;

public interface IElevatorFinder {

	/***
	 * Based on the statuses of elevators, find the most optimal one to which
	 * current pickup request can attach to.
	 * 
	 * @param elevators
	 * @param goalFloor
	 * @param direction
	 * @return
	 */
	int getNextElevatorId(Map<Integer, IElevator> elevators, int goalFloor,
			int direction);
}
