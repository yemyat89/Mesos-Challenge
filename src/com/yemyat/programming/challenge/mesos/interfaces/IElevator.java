package com.yemyat.programming.challenge.mesos.interfaces;

public interface IElevator {
	String getCurrentState();

	int getNextFloor();

	int getElevatorId();

	int getCurrentFloor();

	IGoal getCurrentGoal();

	// Based on the current goal, current floor and next goals, move the
	// elevator
	void update();

	void addNewGoal(IGoal goal);

	boolean hasDoneEveryGoal();

	int getCurrentLoad();
}
