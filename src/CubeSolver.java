import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CubeSolver {
	public static String SOLUTION_FILE = "solutions.txt";
	public static int SOLUTION_NUMBER = 1;
	
	public static void main(String[] args) {
		Cube cube = new Cube(4, 4, 4);
		
		Block block = new Block(1);
		Cubelet cubelet = new Cubelet(1);
		block.startCubelet = cubelet;
		
		Cubelet nCubelet = new Cubelet(1);
		Cubelet n2Cubelet = new Cubelet(1);
		Cubelet eCubelet = new Cubelet(1);
		Cubelet uCubelet = new Cubelet(1);
		nCubelet.attachCubelet(Cubelet.NORTH, n2Cubelet);
		n2Cubelet.attachCubelet(Cubelet.EAST, eCubelet);
		n2Cubelet.attachCubelet(Cubelet.UP, uCubelet);
		
		block.startCubelet.attachCubelet(Cubelet.NORTH, nCubelet);
		
		cube.tryBlockInAllPositions(0);
		
		// output3DArray(cube.spaces);
		
		//cube.attemptToPlace(block.arrayRepresentation, 0, 0, 0);
		//output3DArray(block.arrayRepresentation);
		output3DArray(cube.spaces);
	}
	
	public static void output3DArray(int[][][] array) {
		for (int i = 0; i < array[0][0].length; i++) {
			System.out.println("Layer " + i);
			for (int j = 0; j < array[0].length; j++) {
				for (int k = 0; k < array.length; k++) {
					System.out.print(array[k][j][i] + " ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
	}
	
	public static void writeSolutionToFile(String filepath, int[][][] solution) throws IOException {		
		File file = new File(filepath);
	
		FileWriter writer = new FileWriter(filepath, file.exists());
		PrintWriter printWriter = new PrintWriter(writer);
		
		printWriter.println("Solution" + SOLUTION_NUMBER);
		for (int i = 0; i < solution[0][0].length; i++) {
			printWriter.println("Layer " + i);
			for (int j = 0; j < solution[0].length; j++) {
				for (int k = 0; k < solution.length; k++) {
					printWriter.printf("%02d ", solution[k][j][i]);
				}
				printWriter.println("");
			}
			printWriter.println("");
		}
		
		printWriter.close();
		writer.close();
		
		SOLUTION_NUMBER++;
	}
}
