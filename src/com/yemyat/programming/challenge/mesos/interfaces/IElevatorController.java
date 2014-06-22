package com.yemyat.programming.challenge.mesos.interfaces;

public interface IElevatorController {
	String getStatusAllElevators();

	void requestPickup(int floorNumber, int direction);

	void pressButton(int goalFloor);

	void step();

	boolean allDone();
}
