package it.ifonz.common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import it.ifonz.bean.Coord;

public class FileReader {
	
	public static List<String> readLines(String fileName) throws URISyntaxException, IOException {
		var file = new File(FileReader.class.getResource(fileName).toURI());
		return FileUtils.readLines(file, Charset.defaultCharset());
	}

	public static List<Coord> readCoords(String fileName) throws URISyntaxException, IOException {
		var lines = readLines(fileName);
		return lines.stream().map(s -> new Coord(s.split(",")[0], s.split(" ")[1])).collect(Collectors.toList());
	} 
}
