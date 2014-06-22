package com.yemyat.programming.challenge.mesos;

import java.util.HashMap;
import java.util.Map;

import com.yemyat.programming.challenge.mesos.interfaces.IElevator;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorController;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorFinder;
import com.yemyat.programming.challenge.mesos.interfaces.IGoal;

public class ElevatorController implements IElevatorController {
	
	public static final int DUMMY_DIRECTION = 1000;

	// Using map is to support better way of locating next available elevator
	private Map<Integer, IElevator> elevators;

	// Use dependency injection for elevator finding strategy
	private IElevatorFinder elevatorFinder;

	public ElevatorController(int numberOfElevator,
			IElevatorFinder elevatorFinder) {
		elevators = new HashMap<Integer, IElevator>();
		this.elevatorFinder = elevatorFinder;
		for (int i = 0; i < numberOfElevator; i++) {
			elevators.put(i, new Elevator(i, 1));
		}
	}

	@Override
	public String getStatusAllElevators() {
		StringBuilder sbResults = new StringBuilder();
		for (Integer elvId : elevators.keySet()) {
			String currentState = elevators.get(elvId).getCurrentState();
			sbResults.append(currentState + ", ");
		}
		return sbResults.toString();
	}

	@Override
	public void requestPickup(int floorNumber, int direction) {
		IGoal goal = new Goal(floorNumber);
		int eid = elevatorFinder.getNextElevatorId(elevators);
		elevators.get(eid).addNewGoal(goal);

		// TODO: direction is for future extension to find which elevator would
		// be the most optimal to accept is pickup request. Currently, not used.
		if (direction == DUMMY_DIRECTION) {
			int cf = elevators.get(eid).getCurrentFloor();
			if (cf > floorNumber) {
				direction = -1;
			} else {
				direction = 1;
			}
		}
	}

	@Override
	public void step() {
		if (allDone()) {
			StringBuilder sb = new StringBuilder();
			sb.append("No more task. Elevators' status: (");
			for (Integer elvId : elevators.keySet()) {
				IElevator elv = elevators.get(elvId);
				sb.append("Elevator-" + elv.getElevatorId() + "=>"
						+ elv.getCurrentFloor() + ", ");
			}
			sb.append(")");

			System.out.println(sb.toString());
		} else {
			for (Integer elvId : elevators.keySet()) {
				IElevator elv = elevators.get(elvId);
				elv.update();
			}
		}
	}

	@Override
	public boolean allDone() {
		for (Integer elvId : elevators.keySet()) {
			IElevator elv = elevators.get(elvId);
			if (!elv.hasDoneEveryGoal()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void pressButton(int goalFloor) {
		requestPickup(goalFloor, DUMMY_DIRECTION);
	}

}
