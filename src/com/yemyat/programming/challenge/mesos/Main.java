package com.yemyat.programming.challenge.mesos;

import java.util.Scanner;

import com.yemyat.programming.challenge.mesos.interfaces.IElevatorController;
import com.yemyat.programming.challenge.mesos.interfaces.IElevatorFinder;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		if (args.length < 1) {
			System.out.println("Please specify the number of elevator.");
		}

		try {

			int n = Integer.parseInt(args[0]);

			IElevatorFinder efind = new ElevatorFinder();
			IElevatorController ec = new ElevatorController(n, efind);

			boolean quit = false;

			while (!quit) {
				System.out.print("Input:> ");
				String x = in.nextLine();
				if (x.equals("quit"))
					quit = true;
				else if (x.equals("status")) {
					System.out.println(ec.getStatusAllElevators());
				} else if (x.contains("pickup")) {
					String[] p = x.split(" ");
					if (p.length < 3) {
						System.out
								.println("pickup command needs 2 integer arguments");
						continue;
					}
					ec.requestPickup(Integer.parseInt(p[1]),
							Integer.parseInt(p[2]));
				} else if (x.contains("goto")) {
					String[] p = x.split(" ");
					if (p.length < 2) {
						System.out
								.println("pickup command needs an integer argument");
						continue;
					}
					ec.pressButton(Integer.parseInt(p[1]));
				} else if (x.equals("step")) {
					ec.step();
				} else {
					System.out
							.println("No command found. It must be one of [status, pickup, goto, step or quit]");
				}
			}
		} catch (NumberFormatException e) {
			System.out
					.println("Integer expected but String found. Please restart");
		} catch (Exception e) {
			System.out.println("Something has gone wrong. Please restart.");
		}

	}
}
