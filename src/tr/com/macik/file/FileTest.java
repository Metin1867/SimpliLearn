package tr.com.macik.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import tr.com.macik.tools.UserConsoleInput;

public class FileTest {
	static HashMap<Character, String> menuMap = new HashMap<>();
	static String inFilename, outFilename;
    static FileDescription inFileDesc, outFileDesc;
    static String userContent;
    static StringBuilder fileContent;

	public static void main(String[] args) throws IOException {
		UserConsoleInput userInput = new UserConsoleInput();
		File inFile, outFile;
		String filename;
		boolean activateInpMenu = false;
		boolean activateOutMenu = false;
		
		activateMenu(activateInpMenu, activateOutMenu);

		do {
			char choice = userInput.getChoice(menuMap).charAt(0);
			System.out.println("Auswahl: >" + choice + "<");
			
			if (choice == 'x') {
				break;
			}
			if (choice == '1') {
				inFilename = userInput.getText("Input filename");
				inFileDesc = new FileDescription(inFilename);
				inFilename = inFileDesc.getFullFilename();
				System.out.println("Input file: "+inFilename);
				System.out.println(inFileDesc);
				if (inFileDesc.fileExists)
					activateInpMenu = true;
				else
					activateInpMenu = false;
				activateMenu(activateInpMenu, activateOutMenu);
			}
			if (choice == '2') {
				outFilename = userInput.getText("Output filename");
				outFileDesc = new FileDescription(outFilename);
				outFilename = outFileDesc.getFullFilename();
				System.out.println("Output file: "+outFilename);
				System.out.println(outFileDesc);
				if (outFileDesc.valid)
					activateOutMenu = true;
				else
					activateOutMenu = false;
				activateMenu(activateInpMenu, activateOutMenu);
			}
			if (choice == '3') {
				if (inFileDesc.valid) {
				    fileContent = new StringBuilder();

					String line = "";
					try (BufferedReader br = Files.newBufferedReader(Paths.get(inFileDesc.getFullFilename())))
					{	while((line = br.readLine()) != null) {
							fileContent.append(line);
							fileContent.append("\n");
						}
						br.close();
					}
				} else {
					System.out.println("Error: Input file not defined!");
				}
			}
			if (choice == '4') {
				if (inFileDesc.valid && fileContent != null) {
					System.out.println(fileContent);
				} else {
					System.out.println("Error: Input file not defined or red!");
				}
			}
			if (choice == '5') {
				if (outFileDesc.valid) {
					userContent = userInput.getText("Output file content");

					try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFileDesc.getFullFilename())))
					{
						bw.write(userContent); bw.newLine();
						bw.close();
					}
				} else {
					System.out.println("Error: Output file not defined!");
				}
			}
			if (choice == '6') {
				if (outFileDesc.valid) {
					userContent = userInput.getText("Output file content");
					// append mode
					try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFileDesc.getFullFilename()),
					          StandardOpenOption.CREATE, StandardOpenOption.APPEND))
					{
						bw.write(userContent); bw.newLine();
						bw.close();
					}
				} else {
					System.out.println("Error: Output file not defined!");
				}
			}
			if (choice == '7') {
				if (outFileDesc.valid && fileContent != null) {
					try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFileDesc.getFullFilename())))
					{
						bw.write(fileContent.toString());
						bw.close();
					}
				} else {
					System.out.println("Error: Output file not defined!");
				}
			}
			if (choice == '8') {
			    String directory;
				filename = userInput.getText("Filename");
				System.out.println();
				if (filename == null || "".equals(filename))
						continue;
			    FileDescription fd = new FileDescription(filename);
			    System.out.println(fd.toString());
			}
		} while (true);
	}

	private static void activateMenu(boolean inpMenuEnabled, boolean outMenuEnabled) {
		if (!inpMenuEnabled && !outMenuEnabled) {
			if (menuMap.size() == 0) {
				menuMap.put('1', "Input file");
				menuMap.put('2', "Output file");
				menuMap.put('8', "File info");
				menuMap.put('x', "Exit");
			}
		}

		if (inpMenuEnabled) {
			menuMap.put('1', "Input file " + inFilename);
			menuMap.put('3', "read");
			menuMap.put('4', "show read content");
		} else {
			menuMap.put('1', "Input file");
			menuMap.remove('3');
			menuMap.remove('4');
		}
		
		if (outMenuEnabled) {
			menuMap.put('2', "Output file " + outFilename);
			menuMap.put('5', "write");
			menuMap.put('6', "append");
			menuMap.put('7', "write read content");
		} else {
			menuMap.put('2', "Output file");
			menuMap.remove('5');
			menuMap.remove('6');
			menuMap.remove('7');
		}
	}
}

class FileDescription {
    String filename;
    String path;
    boolean valid;
    String reason;
    boolean directoryExists;
    boolean fileExists;
    boolean fileReadable;
    boolean fileWriteable;
    boolean fileExecuteable;

    public FileDescription(String fullfilename) {
	    Path p;
		try {
			p = Paths.get(fullfilename);
		    File f = new File(fullfilename);
		    if (f.isDirectory()) {
		    	this.path = f.getPath();
		    	p = Files.createTempFile(Paths.get(this.path), null, ".tmp");
		    	f = p.toFile();
		    } else {
		    	this.path = p.getParent().toString();
		    }
		    this.filename = f.getName();
		    
		    File d = new File(path);
		    directoryExists = d.exists();
		    f = new File(getFullFilename());
		    fileExists = f.exists();
		    fileReadable = f.canRead();
		    fileWriteable = f.canWrite();
		    fileExecuteable = f.canExecute();

		    this.valid = true;
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println(e.getMessage());
			// System.out.println(e);
			this.valid = false;
			this.reason = e.toString();
		}

	}

	@Override
    public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Filename:\t\t"); sb.append(filename); sb.append("\n");
	    sb.append("Directory:\t\t"); sb.append(path); sb.append("\n");
	    sb.append("Directory exists:\t"); sb.append(directoryExists); sb.append("\n");
	    sb.append("Full Filename:\t\t"); sb.append(getFullFilename()); sb.append("\n");
	    File f = new File(getFullFilename());
	    sb.append("File exists:\t\t"); sb.append(fileExists); sb.append("\n");
	    sb.append("File readable:\t\t"); sb.append(fileReadable); sb.append("\n");
	    sb.append("File writeable:\t\t"); sb.append(fileWriteable); sb.append("\n");
	    sb.append("File executeable:\t"); sb.append(fileExecuteable); sb.append("\n");

    	return sb.toString();
    }

	public String getFullFilename() {
		return path + File.separatorChar + filename;
	}
}
