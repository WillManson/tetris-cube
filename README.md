# tetris-cube
Here's some Java that will solve the Tetris Cube for you. The Tetris Cube is a toy made by Imagination International. This program should be able to solve all similar puzzles, by changing the sizes of arrays and the shapes of the blocks, as appropriate.

On my machine, the program can reach a solution in about a minute, although it's possible to vary this time quite easily by reordering the blockPlacementOrder array in the Cube class. This is due to the fact that it usually helps to place large, awkward blocks first in order to drastically cut down the available placement possibilities for the remaining blocks.

I'd like to draw particular attention to the Block and Cubelet classes. A given instance of a Block has a single pointer to a Cubelet (here called the startCubelet). Cubelets can then have other Cubelets attached to them by assigning pointers in the neighbours array (see the Cubelet class). I saw this as a neat resolution to the issue of rotating pieces: storing a piece as a 3D array causes some really ugly logic when rotating (including the need to change often the sizes of different layers of the array). By storing the relative locations of Cubelets, to rotate a Block we merely need to permute the pointers in the neighbours array.

The solutions.txt file contains an example output from the program, showing two possible solutions. The program should be able to find all solutions, given enough time.

Slight bug: Due to the rotational symmetry of a few of the pieces, the program will spit out duplicate solutions. A straightforward resolution to this would be to automatically remove duplicates from the output file. This shouldn't be too computationally expensive, since Imagination International states that there are under 10,000 solutions.
