package com.ericsson.msc.group5.utils;

/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 * - Neither the name of Oracle nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior
 * written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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

/**
 * Example to watch a directory (or tree) for changes to files.
 */

public class FolderListener {

	private static WatchService watcher;
	private static Map <WatchKey, Path> keys;
	private static ByteArrayInputStream input;

	//	private RestImportService importService;

	/*public FolderListener(String path) throws IOException {
		Path dir = Paths.get(path);

		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap <WatchKey, Path>();
		importService = new RestImportService();

		// System.out.format("Scanning %s ...\n", dir);
		registerAll(dir);
		System.out.println("Finished registering \nListening for changes to " + dir);
		processEvents();
	}*/

	/*public static void main(String [] args) throws IOException {
		new FolderListener("C:\\Users\\User\\Desktop\\test\\");
	}*/

	public static void begin(String path) {
		Path dir = Paths.get(path);

		try {
			watcher = FileSystems.getDefault().newWatchService();
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

		}
		System.out.println("Finished registering \nListening for changes to " + dir);
		processEvents();
	}

	@SuppressWarnings("unchecked")
	private static <T> WatchEvent <T> cast(WatchEvent <?> event) {
		return (WatchEvent <T>) event;
	}

	private static void processEvents() {
		while (true) {
			// wait for key to be signalled
			WatchKey key;
			try {
				key = watcher.take();
			}
			catch (InterruptedException x) {
				return;
			}

			Path dir = keys.get(key);

			for (WatchEvent <?> event : key.pollEvents()) {
				Kind <?> kind = event.kind();

				// Context for directory entry event is the file name of entry
				WatchEvent <Path> directory = cast(event);
				Path watchedFile = dir.resolve(directory.context());
				String fileName = watchedFile.toString();
				System.out.println(fileName);

				//	importService.applyFileChanges(input);

				// print out event
				System.out.format("%s: %s\n", event.kind().name(), watchedFile);

				// if directory is created, and watching recursively, then
				// register it and its sub-directories
				if (kind == ENTRY_CREATE) {
					try {
						processFile(fileName);
						URL url = new URL("http://localhost:8080/group-project/rest//restImportService/autoImport");
						URLConnection conn = url.openConnection();
						conn.connect();
						if (Files.isDirectory(watchedFile, NOFOLLOW_LINKS)) {
							registerAll(watchedFile);
						}
					}
					catch (IOException x) {
						System.out.println("File not found!");
						continue;
					}
				}
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();
			if ( !valid) {
				keys.remove(key);

				// Stop if all directories are inaccessible
				if (keys.isEmpty()) {
					break;
				}
			}
		}
	}

	private static void processFile(String fileName) {
		input = new ByteArrayInputStream(fileName.getBytes());
	}

	public static ByteArrayInputStream getFile() {
		if (input != null)
			return input;
		else
			return new ByteArrayInputStream(new byte[] { });
	}

	private static void register(Path dir) throws IOException {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
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

	private static void registerAll(final Path start) throws IOException {
		// register directory and sub-directories
		Files.walkFileTree(start, new SimpleFileVisitor <Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				register(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
