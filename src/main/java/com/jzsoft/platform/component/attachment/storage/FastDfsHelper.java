package com.jzsoft.platform.component.attachment.storage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.util.CloseUtil;
import com.jzsoft.platform.util.FileUtil;

public class FastDfsHelper {
	private final static Logger logger = LoggerFactory.getLogger(FastDfsHelper.class);
	static {
		try {
			String classPath = new File(FastDfsHelper.class.getResource("/").getFile()).getCanonicalPath();
			String configFilePath = classPath + File.separator + "conf"+ File.separator +"fdfs_client.conf";
			ClientGlobal.init(configFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FastDFS init fail:"+e.getMessage());
			throw new RuntimeException(e);
		}
	}
	private static TrackerClient trackerClient = new TrackerClient();
	
	public static TrackerServer getConnection(){
		TrackerServer trackerServer = null;
		try {
			trackerServer = trackerClient.getConnection();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("FastDFS connection fail:"+e.getMessage());
			throw new RuntimeException(e);
		}
		return trackerServer;
	}

	public static String upload(File sourceFile) {
		TrackerServer trackerServer = FastDfsHelper.getConnection();
		try {
			StorageServer storageServer = null;
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			byte[] fileBytes = FileUtil.readBytes(sourceFile);
			String serverFilePath = storageClient.upload_file1(fileBytes, AttachmentUtil.getFileExtension(sourceFile.getName()), null);
			return serverFilePath;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FastDFS upload fail:"+e.getMessage());
			throw new RuntimeException(e);
		} finally{
			close(trackerServer);
		}
	}

	public static File download(String filePathName) {
		File targetFile = new File(FileUtil.getTempDir() + FileUtil.getFileNameWithPath(filePathName));
		if (targetFile.exists()) {// 使用缓存的文件
			return targetFile;
		}
		OutputStream local = null;
		TrackerServer trackerServer = FastDfsHelper.getConnection();
		try {
			StorageServer storageServer = null;
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			byte[] fileBytes = storageClient.download_file1(filePathName);
			FileUtil.writeFile(fileBytes, targetFile);
			return targetFile;
		} catch (Exception e) {
			e.printStackTrace();
			if (targetFile.exists()) {
				targetFile.delete();
			}
			logger.error("FastDFS download fail:"+e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(trackerServer);
			CloseUtil.close(local);
		}
	}

	public static void delete(String file_id) {
		TrackerServer trackerServer = FastDfsHelper.getConnection();
		try {
			StorageServer storageServer = null;
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			storageClient.delete_file1(file_id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FastDFS delete fail:"+e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(trackerServer);
		}
	}

	public static void close(TrackerServer trackerServer) {
		if (trackerServer != null) {
			try {
				trackerServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
