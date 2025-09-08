// src/main/java/gotit/file/FileService.java
package gotit.file;

import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static gotit.file.FilePath.FILE_STORE;

public class FileService {
  private static final FileService INSTANCE = new FileService();
  private FileService() {}
  public static FileService getInstance() { return INSTANCE; }

  /** 원본 파일명으로 저장 (권장하지 않음: 충돌 가능) */
  public String saveFile(Part filePart) {
    String fileName = getSubmittedFileName(filePart);
    if (fileName == null || fileName.trim().isEmpty()) return null;
    return saveInternal(filePart, sanitize(fileName));
  }

  /** 지정한 파일명으로 저장 (권장) */
  public String saveFileAs(Part filePart, String targetName) {
    if (targetName == null || targetName.trim().isEmpty()) return null;
    return saveInternal(filePart, sanitize(targetName));
  }

  /** (선택) 파일 삭제 */
  public boolean deleteIfExists(String fileName) {
    if (fileName == null || fileName.isBlank()) return false;
    try {
      Path dir = Path.of(FILE_STORE).toAbsolutePath().normalize();
      Files.createDirectories(dir);
      Path target = dir.resolve(fileName).normalize();
      if (!target.startsWith(dir)) return false; // 경로 탈출 방지
      return Files.deleteIfExists(target);
    } catch (IOException e) {
      return false;
    }
  }

  private String saveInternal(Part filePart, String fileName) {
    try {
      Path dir = Path.of(FILE_STORE).toAbsolutePath().normalize();
      Files.createDirectories(dir);

      Path target = dir.resolve(fileName).normalize();
      if (!target.startsWith(dir)) return null; // 경로 탈출 방지

      try (InputStream is = filePart.getInputStream();
           OutputStream os = new BufferedOutputStream(Files.newOutputStream(target))) {
        byte[] buf = new byte[8192];
        int r;
        while ((r = is.read(buf)) != -1) os.write(buf, 0, r);
      }
      return target.getFileName().toString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String sanitize(String name) {
    return name.replaceAll("[\\p{Cntrl}]", "").replaceAll("[/\\\\]", "_").trim();
  }

  private String getSubmittedFileName(Part part) {
    String header = part.getHeader("content-disposition");
    if (header == null) return null;
    for (String cd : header.split(";")) {
      cd = cd.trim();
      if (cd.startsWith("filename")) {
        String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        return fileName.substring(fileName.lastIndexOf('\\') + 1);
      }
    }
    return null;
  }
}