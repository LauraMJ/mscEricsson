package com.ericsson.msc.group5.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

public class FolderListener implements Runnable {

	private Path filePath;
	private WatchService watchService;

	public FolderListener(String pathStr) {
		filePath = Paths.get(pathStr);
		try {
			watchService = FileSystems.getDefault().newWatchService();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		register(filePath);
	}

	private void register(Path path) {
		try {
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
			System.out.println("PATH REGISTERED");
		}
		catch (IOException e) {
			System.out.println("FAILED TO REGISER PATH");
		}
	}

	@Override
	public void run() {

	}
}
