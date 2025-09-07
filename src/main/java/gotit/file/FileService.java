package gotit.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.http.Part;

import static gotit.file.FilePath.*;

public class FileService {
	private static final FileService instance = new FileService();
	private FileService() {
	}
	
	public static FileService getInstance() {
		return instance;
	}
	public String saveFile(Part filePart) {
		String fileName = getSubmittedFileName(filePart);
		if(fileName != null) fileName = fileName.trim();
		System.out.println("#fileName: " + fileName);
		if(fileName.length() == 0) {
			return null;
		}
		
		long fSize = filePart.getSize();
		
		String uploadPath = FILE_STORE;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		File saveFile = new File(uploadDir, fileName); 
		
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = filePart.getInputStream(); //Source 
			fos = new FileOutputStream(saveFile); //Destination 
			byte bs[] = new byte[512];
			int i = 0;
			while((i = is.read(bs)) != -1) {
				fos.write(bs, 0, i);
			}
			fos.flush();
			
			 return saveFile.getName();
		}catch(IOException ie) {
			return null;
		}finally {
			try {
				if(is != null) is.close();
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}
	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("content-disposition");
		for(String cd : header.split(";")) {
			if(cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;
	}
}
