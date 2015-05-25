
public class DimensionCounter {
	int maxEast = 0;
	int minEast = 0;
	
	int maxNorth = 0;
	int minNorth = 0;
	
	int maxUp = 0;
	int minUp = 0;
	
	public DimensionCounter() {
		
	}
	
	public void updateEast(int newEast) {
		if (newEast > maxEast) {
			maxEast = newEast;
		}
		else if (newEast < minEast) {
			minEast = newEast;
		}
	}
	
	public void updateNorth(int newNorth) {
		if (newNorth > maxNorth) {
			maxNorth = newNorth;
		}
		else if (newNorth < minNorth) {
			minNorth = newNorth;
		}
	}
	
	public void updateUp(int newUp) {
		if (newUp > maxUp) {
			maxUp = newUp;
		}
		else if (newUp < minUp) {
			minUp = newUp;
		}
	}
}
