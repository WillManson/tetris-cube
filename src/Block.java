public class Block {
	public static int ROTATE_FRONT_CLOCKWISE = 0;
	public static int ROTATE_TOP_DOWN = 1;
	public static int ROTATE_RIGHT_TO_LEFT = 2;
	
	public Cubelet startCubelet;
	public int blockNumber;
	public int width, height, depth;
	public int[][][] arrayRepresentation;
	
	public Block(int blockNumber) {
		this.blockNumber = blockNumber;
		startCubelet = new Cubelet(blockNumber);
	}
	
	public void calculateWidthHeightDepthAndRebuildArray() {
		DimensionCounter dimCounter = new DimensionCounter();
		startCubelet.findMinMaxDirections(dimCounter, 0, 0, 0);
		
		width = dimCounter.maxEast - dimCounter.minEast + 1;
		depth = dimCounter.maxNorth - dimCounter.minNorth + 1;
		height = dimCounter.maxUp - dimCounter.minUp + 1;
		
		constructArrayRepresentation(width, 
									 depth,
									 height,
									 0 - dimCounter.minEast,
									 0 - dimCounter.minNorth,
									 0 - dimCounter.minUp);
	}
	
	public void constructArrayRepresentation(int width, int height, int depth, int startX, int startY, int startZ) {
		arrayRepresentation = new int[width][height][depth];
		startCubelet.fillArrayRepresentation(arrayRepresentation, startX, startY, startZ);
	}
	
	public void rotate(int rotationInstruction) {
		startCubelet.rotate(rotationInstruction);
	}
}