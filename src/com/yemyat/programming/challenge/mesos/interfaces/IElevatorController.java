package com.yemyat.programming.challenge.mesos.interfaces;

public interface IElevatorController {

	/***
	 * Get statuses of all elevators it holds. It collects results of each
	 * elevator object's getCurrentState() method.
	 * 
	 * @return String Information about all elevators.
	 */
	String getStatusAllElevators();

	/***
	 * Simulation of pickup request by a person. The idea is on a certain floor
	 * the person is standing outside and press button to get one of the
	 * elevators.
	 * 
	 * @param floorNumber
	 *            int Floor number where the person is standing.
	 * @param direction
	 *            int Direction saying either going up (1) or going down (-1).
	 */
	void requestPickup(int floorNumber, int direction);

	/***
	 * Simulation of when a person inside the elevator presses the buttons
	 * (floor numbers).
	 * 
	 * @param elevatorId
	 *            int Id of the elevator from which press button is triggered.
	 * @param goalFloor
	 *            int Floor number to which the person wishes to go.
	 */
	void pressButton(int elevatorId, int goalFloor);

	/***
	 * Do one unit moving of all elevators. Where to move is determined by each
	 * elevator. This way responsibility is shared by each elevators, reducing
	 * work load of this controller class.
	 */
	void step();

	/***
	 * Check if all elevators have done their respective goals.
	 * 
	 * @return true (All elevators have done their goals); false (Still things
	 *         to do by some elevators).
	 */
	boolean allDone();
}
