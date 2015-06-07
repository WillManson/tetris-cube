# TetrisCube
Here's some Java that will solve the Tetris Cube for you. The Tetris Cube is a toy made by Imagination International.

On my machine, the program can reach a solution in about a minute, although it's possible to vary this time quite easily by reordering the blockPlacementOrder array in the Cube class. This is due to the fact that it usually helps to place large, awkward blocks first in order to drastically cut down the available placement possibilities for the remaining blocks.

I'd like to draw particular attention to the Block and Cubelet classes. A given instance of a Block has a single pointer to a Cubelet (here called the startCubelet). Cubelets can then have other Cubelets attached to them by assigning pointers in the neighbours array (see the Cubelet class). I saw this as a neat resolution to the issue of rotating pieces: storing a piece as a 3D array causes some really ugly logic when rotating (including the need to change often the sizes of different layers of the array). By storing the relative locations of Cubelets, to rotate a Block we merely need to permute the pointers in the neighbours array.
