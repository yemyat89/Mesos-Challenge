package com.yemyat.programming.challenge.mesos;

import java.util.HashMap;
import java.util.Map;

import com.yemyat.programming.challenge.mesos.interfaces.IElevator;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorController;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorFinder;
import com.yemyat.programming.challenge.mesos.interfaces.IGoal;

public class ElevatorControllerImpl implements IElevatorController {

	// Using map is to support better way of locating next available elevator
	private Map<Integer, IElevator> elevators;

	// Use dependency injection for elevator finding strategy
	private IElevatorFinder elevatorFinder;

	public ElevatorControllerImpl(int numberOfElevator,
			IElevatorFinder elevatorFinder) {
		elevators = new HashMap<Integer, IElevator>();
		this.elevatorFinder = elevatorFinder;
		for (int i = 0; i < numberOfElevator; i++) {
			elevators.put(i, new ElevatorImpl(i, 1));
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
		// TODO: direction is for future extension to find which elevator would
		// be the most optimal to accept is pickup request. Currently, not used.
		IGoal goal = new GoalImpl(floorNumber);
		int eid = elevatorFinder.getNextElevatorId(elevators, floorNumber,
				direction);
		elevators.get(eid).addNewGoal(goal);
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
	public void pressButton(int elevatorId, int goalFloor) {
		IGoal goal = new GoalImpl(goalFloor);
		IElevator elv = elevators.get(elevatorId);
		if(elv != null) {
			elv.addNewGoal(goal);
		}
	}

}
