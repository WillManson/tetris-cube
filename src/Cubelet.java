
public class Cubelet {
	public static int NORTH = 0;
	public static int WEST  = 1;
	public static int SOUTH = 2;
	public static int EAST  = 3;
	public static int UP    = 4;
	public static int DOWN  = 5;
	
	public Cubelet[] neighbours;
	public int blockNumber;
	
	public Cubelet(int blockNumber) {
		neighbours = new Cubelet[6];
		this.blockNumber = blockNumber;
	}
	
	public void attachCubelet(int direction, Cubelet cubelet) {
		neighbours[direction] = cubelet;
	}
	
	public void createInDirection(int direction) {
		neighbours[direction] = new Cubelet(blockNumber);
	}
	
	public Cubelet getInDirection(int direction) {
		return neighbours[direction];
	}
	
	public void findMinMaxDirections(DimensionCounter dimCounter, int east, int north, int up) {
		dimCounter.updateEast(east);
		dimCounter.updateNorth(north);
		dimCounter.updateUp(up);
		
		if (neighbours[NORTH] != null) {
			neighbours[NORTH].findMinMaxDirections(dimCounter, east, north + 1, up);
		}
		if (neighbours[WEST] != null) {
			neighbours[WEST].findMinMaxDirections(dimCounter, east - 1, north, up);
		}
		if (neighbours[SOUTH] != null) {
			neighbours[SOUTH].findMinMaxDirections(dimCounter, east, north - 1, up);
		}
		if (neighbours[EAST] != null) {
			neighbours[EAST].findMinMaxDirections(dimCounter, east + 1, north, up);
		}
		if (neighbours[UP] != null) {
			neighbours[UP].findMinMaxDirections(dimCounter, east, north, up + 1);
		}
		if (neighbours[DOWN] != null) {
			neighbours[DOWN].findMinMaxDirections(dimCounter, east, north, up - 1);
		}
	}
	
	public void fillArrayRepresentation(int[][][] aR, int x, int y, int z) {
		aR[x][y][z] = blockNumber;
		
		if (neighbours[NORTH] != null) {
			neighbours[NORTH].fillArrayRepresentation(aR, x, y + 1, z);
		}
		if (neighbours[WEST] != null) {
			neighbours[WEST].fillArrayRepresentation(aR, x - 1, y, z);
		}
		if (neighbours[SOUTH] != null) {
			neighbours[SOUTH].fillArrayRepresentation(aR, x, y - 1, z);
		}
		if (neighbours[EAST] != null) {
			neighbours[EAST].fillArrayRepresentation(aR, x + 1, y, z);
		}
		if (neighbours[UP] != null) {
			neighbours[UP].fillArrayRepresentation(aR, x, y, z + 1);
		}
		if (neighbours[DOWN] != null) {
			neighbours[DOWN].fillArrayRepresentation(aR, x, y, z - 1);
		}
	}
	
	public void rotate(int rotationInstruction) {
		if (rotationInstruction == Block.ROTATE_FRONT_CLOCKWISE) {
			Cubelet temp = neighbours[UP];
			neighbours[UP] = neighbours[WEST];
			neighbours[WEST] = neighbours[DOWN];
			neighbours[DOWN] = neighbours[EAST];
			neighbours[EAST] = temp;
		}
		else if (rotationInstruction == Block.ROTATE_TOP_DOWN) {
			Cubelet temp = neighbours[UP];
			neighbours[UP] = neighbours[NORTH];
			neighbours[NORTH] = neighbours[DOWN];
			neighbours[DOWN] = neighbours[SOUTH];
			neighbours[SOUTH] = temp;
		}
		else if (rotationInstruction == Block.ROTATE_RIGHT_TO_LEFT) {
			Cubelet temp = neighbours[SOUTH];
			neighbours[SOUTH] = neighbours[EAST];
			neighbours[EAST] = neighbours[NORTH];
			neighbours[NORTH] = neighbours[WEST];
			neighbours[WEST] = temp;
		}
		
		if (neighbours[NORTH] != null) {
			neighbours[NORTH].rotate(rotationInstruction);
		}
		if (neighbours[WEST] != null) {
			neighbours[WEST].rotate(rotationInstruction);
		}
		if (neighbours[SOUTH] != null) {
			neighbours[SOUTH].rotate(rotationInstruction);
		}
		if (neighbours[EAST] != null) {
			neighbours[EAST].rotate(rotationInstruction);
		}
		if (neighbours[UP] != null) {
			neighbours[UP].rotate(rotationInstruction);
		}
		if (neighbours[DOWN] != null) {
			neighbours[DOWN].rotate(rotationInstruction);
		}
	}
}
