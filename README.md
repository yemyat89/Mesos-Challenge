Question

Design and implement an elevator control system. What data structures,
interfaces and algorithms will you need? Your elevator control system should
be able to handle a few elevators -- up to 16.

You can use the language of your choice to implement an elevator control
system. In the end, your control system should provide an interface for:

  * Querying the state of the elevators (what floor are they on and where they
    are going),

  * receiving an update about the status of an elevator,

  * receiving a pickup request,

  * time-stepping the simulation.

For example, we could imagine in Scala an interface like this:

  trait ElevatorControlSystem {
    def status(): Seq[(Int, Int, Int)]
    def update(Int, Int, Int)
    def pickup(Int, Int)
    def step()
  }

Here we have chosen to represent elevator state as 3 integers:

  Elevator ID, Floor Number, Goal Floor Number

An update alters these numbers for one elevator. A pickup request is two
integers:

  Pickup Floor, Direction (negative for down, positive for up)

This is not a particularly nice interface, and leaves some questions open. For
example, the elevator state only has one goal floor; but it is conceivable
that an elevator holds more than one person, and each person wants to go to a
different floor, so there could be a few goal floors queued up.

How to run
Type "java -jar ecs.jar N" where N is the number of elevator. Then a prompt will show and you can type one of the following commands (status, pickup, goto, step, quit). The following is a sample interaction.

--------------------
~ java -jar ecs.jar 2
Input:> pickup 5 -1
Input:> status
(elevator_id=0, current_floor=1, current_goal=[ 5], goals_todo=[ ]), (elevator_id=1, current_floor=1, current_goal=[ ], goals_todo=[ ]), 
Input:> pickup 3 1
Input:> goto 2
Input:> goto 8
Input:> status
(elevator_id=0, current_floor=1, current_goal=[ 5], goals_todo=[ (2) ]), (elevator_id=1, current_floor=1, current_goal=[ 3], goals_todo=[ (8) ]), 
Input:> status
(elevator_id=0, current_floor=1, current_goal=[ 5], goals_todo=[ (2) ]), (elevator_id=1, current_floor=1, current_goal=[ 3], goals_todo=[ (8) ]), 
Input:> step
Input:> step
Elevator 1 has reached the floor 3
Input:> step
Input:> step
Elevator 0 has reached the floor 5
Input:> step
Input:> status
(elevator_id=0, current_floor=4, current_goal=[ 2], goals_todo=[ ]), (elevator_id=1, current_floor=6, current_goal=[ 8], goals_todo=[ ]), 
Input:> step
Input:> step
Elevator 0 has reached the floor 2
Elevator 1 has reached the floor 8
Input:> step
No more task. Elevators' status: (Elevator-0=>2, Elevator-1=>8, )
Input:> step
No more task. Elevators' status: (Elevator-0=>2, Elevator-1=>8, )
Input:> status
(elevator_id=0, current_floor=2, current_goal=[ ], goals_todo=[ ]), (elevator_id=1, current_floor=8, current_goal=[ ], goals_todo=[ ]), 
Input:> quit
--------------------

Solution idea
ElevatorController attach a request to next best elevator. Request can be either pickup or goto. The former is simulation of when a person is waiting outside of elevator bank and want to go (up/down). The latter is when the person is inside and press floor buttons to go. Internally, both requests are tranformed into goals. Determining which elevator to attach the current request is charaterized by ElevatorFinder. Currently, it returns the elevator with lowest load. This can be improved in several ways.

Typing step let all elevators to do their task. Assumption here is calling step will make elevators go next floor (up/down). Deciding whether to go up or down is determined by each elevator (using their current floor, next goal, etc). This way ElevatorController does not have to worry about this responsibility. The command pickup requires argument to say if it is up or down. This argument is currently not used but it is very useful for determining the next available elevator when a new request comes in.
