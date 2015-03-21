// package com.ericsson.msc.group5.utils;
//
// import javax.annotation.PostConstruct;
// import javax.ejb.Singleton;
// import javax.ejb.Startup;
//
// @Startup
// @Singleton
// public class FolderListenerUtil {
//
// @PostConstruct
// public void start() {
// FolderListener folderListener = new FolderListener("C:\\Users\\User\\Desktop\\test\\");
// Thread folderListenerThread = new Thread(folderListener);
// if (folderListener.isPathRegistered())
// folderListenerThread.start();
// else
// System.out.println("LISTENER FAILED TO START");
// }
//}
