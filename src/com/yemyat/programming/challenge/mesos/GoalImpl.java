package com.yemyat.programming.challenge.mesos;

import com.yemyat.programming.challenge.mesos.interfaces.IGoal;

public class GoalImpl implements IGoal {

	private int destinationFloor;

	public GoalImpl(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	@Override
	public int getDestinationFloor() {
		return this.destinationFloor;
	}

}
