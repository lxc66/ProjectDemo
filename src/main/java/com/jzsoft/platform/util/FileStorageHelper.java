package com.jzsoft.platform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jzsoft.platform.core.spring.SpringContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileStorageHelper {

	public static String getStorageRootPath(){
		return SpringContextHolder.getRootRealPath();
	}
	
	public static String getStorageTempPath(){
		String tempPath = FileUtil.getTempDir()+"tempupload";
		File dir = new File(tempPath);
		if(!dir.exists())dir.mkdirs();
		return tempPath;
	}
	

	/**
	 * 转存文件
	 * @param sourcePath
	 * @param destPath
	 */
	public static void transferFile(String sourcePath, String destPath){
		transferFile(new File(sourcePath), destPath);
	}
	
	/**
	 * 转存文件
	 * @param sourceFile
	 * @param destPath
	 */
	public static void transferFile(File sourceFile, String destPath){
		if(sourceFile==null || !sourceFile.exists()){
			log.error("The source file does not exist!!");
			throw new RuntimeException("The source file does not exist!!");
		}
		try {
			transferFile(new FileInputStream(sourceFile), destPath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 转存文件
	 * @param source
	 * @param destPath
	 */
	public static void transferFile(InputStream source, String destPath){
		String rootPath = getStorageRootPath();
		doTransfer(source, destPath, rootPath);
	}

	private static void doTransfer(InputStream source, String destPath, String rootPath) {
		if(source==null){
			log.error("Source input stream is null!!");
			throw new RuntimeException("Source input stream is null!!");
		}
		if(StringUtils.isBlank(destPath)){
			log.error("destPath is null!!");
			throw new RuntimeException("destPath is null!!");
		}
		String relativePath = FileUtil.getFilePath(destPath);
		try {
			String fileTransferDir = rootPath+File.separator+ relativePath;
			File dir = new File(fileTransferDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			String fileAbsolutePath = rootPath+File.separator+ destPath;
			File destFile = new File(fileAbsolutePath);
			FileUtils.copyInputStreamToFile(source, destFile);
		} catch (Exception e) {
			log.error("transfer file error!!");
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 转存临时文件
	 * @param source
	 * @param destPath
	 */
	public static void transferTempFile(InputStream source, String fileName){
		String rootPath = getStorageTempPath();
		doTransfer(source, fileName, rootPath);
	}
	
	/**
	 * 转存临时文件
	 * @param source
	 * @param destPath
	 */
	public static void transferTempFile(MultipartFile source, String fileName){
		if(source==null){
			log.error("transfer to temporary files parameter error!! MultipartFile is null");
			throw new RuntimeException("transfer to temporary files parameter error!! MultipartFile is null");
		}
		try {
			transferTempFile(source.getInputStream(), fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
