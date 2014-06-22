package com.yemyat.programming.challenge.mesos.interfaces;

import java.util.Map;

public interface IElevatorFinder {
	int getNextElevatorId(Map<Integer, IElevator> elevators);
}
