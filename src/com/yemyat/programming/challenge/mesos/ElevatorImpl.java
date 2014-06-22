package com.yemyat.programming.challenge.mesos;

import java.util.ArrayList;
import java.util.List;

import com.yemyat.programming.challenge.mesos.interfaces.IElevator;
import com.yemyat.programming.challenge.mesos.interfaces.IGoal;

public class ElevatorImpl implements IElevator {

	private int elevatorId;
	private int currentFloor;
	private IGoal currentGoal;
	private List<IGoal> goals; // TODO: Improve with Queue

	public ElevatorImpl(int elevatorId, int currentFloor) {
		this.elevatorId = elevatorId;
		this.currentFloor = currentFloor;
		this.currentGoal = null;
		this.goals = new ArrayList<IGoal>();
	}

	@Override
	public String getCurrentState() {
		StringBuilder sbCurrentGoal = new StringBuilder();
		sbCurrentGoal.append("[ ");
		if (currentGoal != null) {
			sbCurrentGoal.append(currentGoal.getDestinationFloor());
		}
		sbCurrentGoal.append("]");

		StringBuilder sbAllGoals = new StringBuilder();
		sbAllGoals.append("[ ");
		for (IGoal goal : goals) {
			sbAllGoals.append("(" + goal.getDestinationFloor() + ") ");
		}
		sbAllGoals.append("]");

		return "(elevator_id=" + this.elevatorId + ", current_floor="
				+ this.currentFloor + ", current_goal="
				+ sbCurrentGoal.toString() + ", goals_todo="
				+ sbAllGoals.toString() + ")";
	}

	@Override
	public int getNextFloor() {
		if (currentGoal != null) {
			int increment = 0;
			if (currentFloor > currentGoal.getDestinationFloor()) {
				increment = -1;
			} else if (currentFloor == currentGoal.getDestinationFloor()) {
				increment = 0;
			} else {
				increment = 1;
			}
			return (currentFloor + increment);
		}
		return this.currentFloor;
	}

	@Override
	public void addNewGoal(IGoal goal) {
		if (currentGoal == null) {
			currentGoal = goal;
		} else {
			this.goals.add(goal);
		}
	}

	@Override
	public void update() {
		if (currentGoal != null) {
			currentFloor = getNextFloor();
			if (currentFloor == currentGoal.getDestinationFloor()) {
				System.out.println("Elevator " + elevatorId
						+ " has reached the floor " + currentFloor);
				currentGoal = null;
				if (goals.size() > 0) {
					currentGoal = goals.get(0);
					goals.remove(0);
				}
			}
		}
	}

	@Override
	public int getCurrentFloor() {
		return this.currentFloor;
	}

	@Override
	public IGoal getCurrentGoal() {
		return this.currentGoal;
	}

	@Override
	public boolean hasDoneEveryGoal() {
		return (goals.size() == 0 && currentGoal == null);
	}

	@Override
	public int getCurrentLoad() {
		int count = (currentGoal == null) ? 0 : 1;
		count += goals.size();
		return count;
	}

	@Override
	public int getElevatorId() {
		return this.elevatorId;
	}

}
