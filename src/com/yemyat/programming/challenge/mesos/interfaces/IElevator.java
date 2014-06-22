package com.yemyat.programming.challenge.mesos.interfaces;

/***
 * Interface for an elevator. This interface defines generic functions that an
 * elevator should provide. The concrete elevator can be any brand or model.
 * 
 * @author Ye Myat Thein
 * 
 */
public interface IElevator {

	/***
	 * Get current state information of the elevator.
	 * 
	 * @return String that describes current floor, current goal and goals to do
	 *         in queue.
	 */
	String getCurrentState();

	/***
	 * Get next floor based on current floor and current goal. If either no
	 * current goal or the goal floor is same as the current floor, the current
	 * floor number is returned.
	 * 
	 * @return int Floor number to which the elevator has just move.
	 */
	int getNextFloor();

	/***
	 * Return the elevator's ID
	 * 
	 * @return int Elevator's ID
	 */
	int getElevatorId();

	int getCurrentFloor();

	/***
	 * Get the currently active goal of the elevator.
	 * 
	 * @return IGoal Object representing a goal.
	 */
	IGoal getCurrentGoal();

	/***
	 * Based on the current goal, current floor and next goals, move the
	 * elevator to next floor (up or down).
	 */
	void update();

	/***
	 * Add new goals to the elevator's queue.
	 * 
	 * @param goal
	 *            Object representing a goal.
	 */
	void addNewGoal(IGoal goal);

	/***
	 * To check the elevator has satisfy all the goals.
	 * 
	 * @return true if it finishes for all goals; false otherwise.
	 */
	boolean hasDoneEveryGoal();

	int getCurrentLoad();
}
