package it.unibz.ata.project6.ted;

public class LabelGenerator {

	public static String[] generate(int nLabels) {
		String[] labels = new String[nLabels];
		for (int i = 0; i < nLabels; i++) {
			labels[i] = Character.toString(Character.toChars(i+1)[0]);
		}
		return labels;
	}
	
	public static void main(String args[]) {
		int n = 1000;
		String[] lables = generate(n);
		for (int i = 0; i < n; i++) {
			System.out.println(i + ": " + lables[i]);
		}
	}
}
