package it.unibz.apeer.thesis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestfileReader {
	
	BufferedReader reader_;
	
	public TestfileReader(String path) throws FileNotFoundException {
		this(new FileInputStream(path));
	}
	
	public TestfileReader(InputStream input) {
		reader_ = new BufferedReader(new InputStreamReader(input));
	}
	
	public TreeCodeWithID nextTreeCode() throws IOException {
		TreeCodeWithID tree = null;
		String line = reader_.readLine();
		if (line != null) {
			tree = new TreeCodeWithID();
			int skipPos = line.indexOf(':');
			tree.id = Integer.parseInt(line.substring(0, skipPos));
			tree.code = line.substring(skipPos + 1, line.length());
		}
		return tree;
	}
}

