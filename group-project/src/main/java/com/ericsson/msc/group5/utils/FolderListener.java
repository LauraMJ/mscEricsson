package com.ericsson.msc.group5.utils;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.ejb.DataImportServiceEJB;

public class FolderListener implements Runnable {

	@Inject
	private DataImportService dataImport;

	private WatchService watchService;
	private Map <WatchKey, Path> keys;
	private boolean pathRegistered = false;
	private ByteArrayInputStream input;

	public FolderListener(String pathStr) {
		Path filePath = Paths.get(pathStr);
		try {
			watchService = FileSystems.getDefault().newWatchService();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		keys = new HashMap <WatchKey, Path>();
		registerAll(filePath);
		pathRegistered = true;
		System.out.println("Finished registering");
		System.out.println("Listening for changes in " + pathStr);
	}

	private void register(Path dir) {
		WatchKey key = null;
		try {
			key = dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		keys.put(key, dir);
	}

	private void registerAll(final Path start) {
		// register directory and sub-directories
		try {
			Files.walkFileTree(start, new SimpleFileVisitor <Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					register(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			WatchKey key = null;
			try {
				key = watchService.take();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			Path dir = keys.get(key);

			if (key != null) {
				for (WatchEvent <?> event : key.pollEvents()) {
					Kind <?> kind = event.kind();
					WatchEvent <Path> directory = castEvent(event);
					Path watchedFile = dir.resolve(directory.context());
					String fileName = watchedFile.toString();
					System.out.println(fileName);

					switch (kind.name()) {
						case "ENTRY_CREATE": {
							System.out.println("Created: " + event.context());
							if (importFile(fileName))
								System.out.println("Data imported successfully");
							else
								System.out.println("Problem with import");
							break;
						}
						case "ENTRY_MODIFY": {
							System.out.println("Modified: " + event.context());
							break;
						}
						case "ENTRY_DELETE": {
							System.out.println("Deleted: " + event.context());
							break;
						}
						default:
							break;
					}
				}
			}

			//reset is invoked to put the key back to ready state
			boolean valid = key.reset();

			//If the key is invalid,  exit.
			if ( !valid) {
				break;
			}
		}
	}

	//FIXME Path being passed in is correct, yet getting a null pointer when attempting to send the worksheet to dataImport
	public boolean importFile(String path) {

		String resultString = "";
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(new File(path));
			wb.write(fileOut);
			if (wb.getBytes().length == 0)
				System.out.println("Nothing got written!");
			else
				System.out.println("#bytes: " + wb.getBytes().length);
			fileOut.close();
			dataImport.importSpreadsheet(wb);
			resultString = "Time taken: " + DataImportServiceEJB.duration + " milliseconds.";
			System.out.println(resultString);
			return true;
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			System.out.println(resultString);
			e.printStackTrace();
			return false;
		}
	}

	//		private void processFile(String fileName) {
	//			input = new ByteArrayInputStream(fileName.getBytes());
	//		}
	//	
	//
	//	public ByteArrayInputStream getFile() {
	//			if (input != null)
	//				return input;
	//			else
	//				return new ByteArrayInputStream(new byte[] { });
	//		}

	private static <T> WatchEvent <T> castEvent(WatchEvent <?> event) {
		return (WatchEvent <T>) event;
	}

	public boolean isPathRegistered() {
		return pathRegistered;
	}
}
