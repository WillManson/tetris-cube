import java.io.IOException;


public class Cube {
	Block[] blocks;
	int[][][] spaces;
	int width, height, depth;
	int[] blockPlacementOrder;
	
	public Cube(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		spaces = new int[width][height][depth];
		
		initialiseBlocks();
		
		blockPlacementOrder = new int[] { 3, 5, 8, 1, 4, 11, 9, 7, 6, 2, 10, 0 };
	}
	
	public void removeBlock(int blockNumber) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < depth; k++) {
					if (spaces[i][j][k] == blockNumber) {
						spaces[i][j][k] = 0;
					}
				}
			}
		}
	}
	
	public boolean attemptToPlace(int[][][] blockAsArray, int x, int y, int z) {
		for (int i = 0; i < blockAsArray.length; i++) {
			for (int j = 0; j < blockAsArray[0].length; j++) {
				for (int k = 0; k < blockAsArray[0][0].length; k++) {
					if (blockAsArray[i][j][k] != 0) {
						if (spaces[x + i][y + j][z + k] == 0) {
							spaces[x + i][y + j][z + k] = blockAsArray[i][j][k];
						}
						else {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public void tryBlockInAllPositions(int blockInOrder) {
		Block block = blocks[blockPlacementOrder[blockInOrder]];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				block.calculateWidthHeightDepthAndRebuildArray();
				iterateThroughAllPositions(block, blockInOrder);
				block.rotate(Block.ROTATE_FRONT_CLOCKWISE);
			}
			block.rotate(Block.ROTATE_TOP_DOWN);
		}
		
		block.rotate(Block.ROTATE_RIGHT_TO_LEFT);
		for (int j = 0; j < 4; j++) {
			block.calculateWidthHeightDepthAndRebuildArray();
			iterateThroughAllPositions(block, blockInOrder);
			block.rotate(Block.ROTATE_FRONT_CLOCKWISE);
		}
		
		block.rotate(Block.ROTATE_RIGHT_TO_LEFT);
		block.rotate(Block.ROTATE_RIGHT_TO_LEFT);
		for (int j = 0; j < 4; j++) {
			block.calculateWidthHeightDepthAndRebuildArray();
			iterateThroughAllPositions(block, blockInOrder);
			block.rotate(Block.ROTATE_FRONT_CLOCKWISE);
		}
		
		iterateThroughAllPositions(block, blockInOrder);
	}

	public void iterateThroughAllPositions(Block block, int blockInOrder) {
		for (int i = 0; i <= width - block.width; i++) {
			for (int j = 0; j <= depth - block.depth; j++) {
				for (int k = 0; k <= height - block.height; k++) {
					boolean outcome = attemptToPlace(block.arrayRepresentation, i, j, k);
					
					if (outcome && blockInOrder == 11) {
						System.out.println("Solution");
						CubeSolver.output3DArray(spaces);
						try {
							CubeSolver.writeSolutionToFile(CubeSolver.SOLUTION_FILE, spaces);
						}
						catch (IOException exception) {
							System.out.println("Write error");
						}
						System.out.println("");
					}
					else if (outcome) {
						tryBlockInAllPositions(blockInOrder + 1);
					}
					
					removeBlock(block.blockNumber);
				}
			}
		}
	}
	
	public void initialiseBlocks() {
		blocks = new Block[12];
		
		// Red 5-piece square shape (with sticky out bit)
		blocks[0] = new Block(1);
		blocks[0].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[0].startCubelet.createInDirection(Cubelet.UP);
		blocks[0].startCubelet.createInDirection(Cubelet.WEST);
		blocks[0].startCubelet.getInDirection(Cubelet.WEST).createInDirection(Cubelet.NORTH);
		
		// Red 5-piece L shape
		blocks[1] = new Block(2);
		blocks[1].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[1].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		blocks[1].startCubelet.createInDirection(Cubelet.EAST);
		blocks[1].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.EAST);
		
		// Red 5-piece line shape (with sticky out bit)
		blocks[2] = new Block(3);
		blocks[2].startCubelet.createInDirection(Cubelet.WEST);
		blocks[2].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[2].startCubelet.createInDirection(Cubelet.EAST);
		blocks[2].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.EAST);
		
		// Red 6-piece horrible shape
		blocks[3] = new Block(4);
		blocks[3].startCubelet.createInDirection(Cubelet.WEST);
		blocks[3].startCubelet.getInDirection(Cubelet.WEST).createInDirection(Cubelet.UP);
		blocks[3].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[3].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.EAST);
		blocks[3].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		
		// Yellow symmetrical 5-piece
		blocks[4] = new Block(5);
		blocks[4].startCubelet.createInDirection(Cubelet.WEST);
		blocks[4].startCubelet.createInDirection(Cubelet.EAST);
		blocks[4].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[4].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.UP);
		
		// Yellow symmetrical 6-piece
		blocks[5] = new Block(6);
		blocks[5].startCubelet.createInDirection(Cubelet.WEST);
		blocks[5].startCubelet.createInDirection(Cubelet.SOUTH);
		blocks[5].startCubelet.createInDirection(Cubelet.EAST);
		blocks[5].startCubelet.createInDirection(Cubelet.UP);
		blocks[5].startCubelet.getInDirection(Cubelet.UP).createInDirection(Cubelet.NORTH);
		
		// Yellow 5-piece wiggly shape
		blocks[6] = new Block(7);
		blocks[6].startCubelet.createInDirection(Cubelet.DOWN);
		blocks[6].startCubelet.getInDirection(Cubelet.DOWN).createInDirection(Cubelet.WEST);
		blocks[6].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[6].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.EAST);
		
		// Yellow 5-piece with long straight edge
		blocks[7] = new Block(8);
		blocks[7].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[7].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		blocks[7].startCubelet.createInDirection(Cubelet.EAST);
		blocks[7].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.UP);
		
		// Blue 6-piece big L with sticky out bit at far end of one leg
		blocks[8] = new Block(9);
		blocks[8].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[8].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		blocks[8].startCubelet.createInDirection(Cubelet.EAST);
		blocks[8].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.EAST);
		blocks[8].startCubelet.getInDirection(Cubelet.EAST).getInDirection(Cubelet.EAST).createInDirection(Cubelet.UP);
		
		// Blue 6-piece big L with sticky out bit half-way down one leg
		blocks[9] = new Block(10);
		blocks[9].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[9].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		blocks[9].startCubelet.createInDirection(Cubelet.EAST);
		blocks[9].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.EAST);
		blocks[9].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.UP);
		
		// Blue 5-piece wiggly (only 2D blue piece)
		blocks[10] = new Block(11);
		blocks[10].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[10].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.NORTH);
		blocks[10].startCubelet.createInDirection(Cubelet.EAST);
		blocks[10].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.SOUTH);
		
		// Blue 5-piece weird wiggly piece
		blocks[11] = new Block(12);
		blocks[11].startCubelet.createInDirection(Cubelet.NORTH);
		blocks[11].startCubelet.getInDirection(Cubelet.NORTH).createInDirection(Cubelet.WEST);
		blocks[11].startCubelet.createInDirection(Cubelet.EAST);
		blocks[11].startCubelet.getInDirection(Cubelet.EAST).createInDirection(Cubelet.UP);
	}
}
