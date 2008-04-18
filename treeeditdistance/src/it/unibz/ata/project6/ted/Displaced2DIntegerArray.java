package it.unibz.ata.project6.ted;

public class Displaced2DIntegerArray {
	
	private int displacementX_;
	private int displacementY_;
	private int array_[][];
	
	public Displaced2DIntegerArray(int startX, int endX, int startY, int endY) {
		displacementX_ = startX;
		displacementY_ = startY;
		array_ = new int[endX - startX][endY - startY];
	}
	
	public void set(int x, int y, int value) {
		array_[displacementX_ + x][displacementY_ + y] = value;
	}
	
	public int get(int x, int y) {
		return array_[displacementX_ + x][displacementY_ + y];
	}
}
