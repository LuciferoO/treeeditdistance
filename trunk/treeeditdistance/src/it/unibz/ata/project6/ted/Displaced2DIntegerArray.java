package it.unibz.ata.project6.ted;

public class Displaced2DIntegerArray {
	
	private int displacementX_;
	private int displacementY_;
	private int array_[][];
	
	public Displaced2DIntegerArray(int startX, int endX, int startY, int endY) {
		displacementX_ = startX;
		displacementY_ = startY;
		array_ = new int[endX - startX + 1][endY - startY + 1];
	}
	
	public void set(int x, int y, int value) {
		array_[x - displacementX_][y - displacementY_] = value;
	}
	
	public int get(int x, int y) {
		return array_[x - displacementX_][y - displacementY_];
	}
}
