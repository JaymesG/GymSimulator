# GymSimulator
CS 511 - Concurrent Programming Project

This is the gym simulation for homework 2 of CS511 Concurrent Programming
It was meant to teach us how to control interleavings with semaphores by
simulating a gym with 10,000 clients all of which have their own routine
(or list) of exercises. Each exercise consists of a duration, an apparatus,
and a set of small, medium, and large weight plates that vary in amount. There
are only 5 of each apparatus' and there are a max of 20, 30, and 50, small, medium,
and large plates respectively. Each client can only use what equipment is avaiable
and only one client can access the equipment at a time. 30 threads (clients) are
able to work at once.

NOTE: Assignment2.java is the main file.
NOTE: This simulation runs much faster when the amount of clients is reduced.
