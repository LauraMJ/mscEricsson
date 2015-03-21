package com.ericsson.msc.group5.utils;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.ByteArrayInputStream;
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

public class FolderListener implements Runnable {

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
		try {
			registerAll(filePath);
			pathRegistered = true;
			System.out.println("Finished registering");
			System.out.println("Listening for changes in " + pathStr);
		}
		catch (IOException e) {
			//e.printStackTrace();
			pathRegistered = false;
			System.out.println("Failed to register");
		}
		/*Path dir = Paths.get(pathStr);

		try {
			watchService = FileSystems.getDefault().newWatchService();
		}
		catch (IOException e) {
			e.printStackTrace();

		}
		keys = new HashMap <WatchKey, Path>();
		//		importService = new RestImportService();

		// System.out.format("Scanning %s ...\n", dir);
		try {
			registerAll(dir);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("FAILED TO REGISTER");
		}*/
	}

	private void register(Path dir) throws IOException {
		WatchKey key = dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		Path prev = keys.get(key);
		/*if (prev == null) {
			System.out.format("register: %s\n", dir);
		}
		else {
			if ( !dir.equals(prev)) {
				System.out.format("update: %s -> %s\n", prev, dir);
			}
		}*/
		keys.put(key, dir);
	}

	private void registerAll(final Path start) throws IOException {
		// register directory and sub-directories
		Files.walkFileTree(start, new SimpleFileVisitor <Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				register(dir);
				return FileVisitResult.CONTINUE;
			}
		});
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
						case "ENTRY_CREATE":
							System.out.println("Created: " + event.context());
							break;
						case "ENTRY_MODIFY":
							System.out.println("Modified: " + event.context());
							break;
						case "ENTRY_DELETE":
							System.out.println("Deleted: " + event.context());
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

	private void processFile(String fileName) {
		input = new ByteArrayInputStream(fileName.getBytes());
	}

	public ByteArrayInputStream getFile() {
		if (input != null)
			return input;
		else
			return new ByteArrayInputStream(new byte[] { });
	}

	private static <T> WatchEvent <T> castEvent(WatchEvent <?> event) {
		return (WatchEvent <T>) event;
	}

	public boolean isPathRegistered() {
		return pathRegistered;
	}
}
