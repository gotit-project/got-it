// src/main/java/gotit/file/UploadAvatarController.java
package gotit.file;

import gotit.model.User;
import gotit.auth.AuthDAO;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@WebServlet("/avatar/upload")
@MultipartConfig(
  fileSizeThreshold = 64 * 1024,
  maxFileSize = 5L * 1024 * 1024,    // 5MB
  maxRequestSize = 6L * 1024 * 1024  // 6MB
)
public class UploadFileController extends HttpServlet {
  private final FileService fileService = FileService.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding(StandardCharsets.UTF_8.name());

    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("loginUser") == null) {
      resp.sendRedirect(req.getContextPath() + "/login");
      return;
    }
    User loginUser = (User) session.getAttribute("loginUser");
    long userId = loginUser.getUserId();

    String referer = req.getHeader("Referer");
    if (referer == null || referer.isBlank()) {
      // 적절한 프로필 페이지로 교체
      referer = req.getContextPath() + "/mypage/profile";
    }

    try {
      Part part = req.getPart("avatar");
      if (part == null || part.getSize() == 0) {
        // 플래시를 새 세션에 심어야 하므로 일단 기존 세션 사용
        session.setAttribute("flash", "파일이 없습니다.");
        resp.sendRedirect(referer);
        return;
      }

      String ct = safeLower(part.getContentType());
      if (!( "image/png".equals(ct) || "image/jpeg".equals(ct) || "image/webp".equals(ct) || "image/gif".equals(ct) )) {
        session.setAttribute("flash", "이미지(PNG/JPG/WEBP/GIF)만 업로드 가능합니다.");
        resp.sendRedirect(referer);
        return;
      }

      String submitted = getSubmittedFileName(part);
      String ext = guessExtension(submitted, ct); // .png/.jpg/.webp/.gif
      String saveName = "u" + userId + "_" + System.currentTimeMillis() + ext;

      String storedName = fileService.saveFileAs(part, saveName);
      if (storedName == null) {
        session.setAttribute("flash", "파일 저장에 실패했습니다.");
        resp.sendRedirect(referer);
        return;
      }

      // (선택) 이전 파일 삭제
      // String old = loginUser.getImgName();
      // if (old != null && !old.isBlank()) fileService.deleteIfExists(old);

      // DB 업데이트
      AuthDAO.getInstance().updateImgName(userId, storedName);

      // 최신 사용자 재조회(권장)
      User refreshed = AuthDAO.getInstance().findById(userId);
      if (refreshed == null) {
        refreshed = loginUser;
        refreshed.setImgName(storedName);
      }

      // ----- 세션 재발급 -----
      HttpSession oldSession = req.getSession(false);
      if (oldSession != null) {
        try { oldSession.invalidate(); } catch (IllegalStateException ignore) {}
      }
      HttpSession newSession = req.getSession(true); // 새 세션 발급
      newSession.setAttribute("loginOkUser", refreshed);
      newSession.setAttribute("flash", "프로필 사진이 변경되었습니다.");

      // PRG
      resp.sendRedirect(referer);

    } catch (Exception e) {
      e.printStackTrace();
      // 예외 시에도 세션 재발급까지는 필요 없으니 기존 세션에 메시지
      session = req.getSession(false);
      if (session != null) session.setAttribute("flash", "업로드 처리 중 오류가 발생했습니다.");
      resp.sendRedirect(referer);
    }
  }

  private static String safeLower(String s) { return s == null ? null : s.toLowerCase(Locale.ROOT); }

  private static String getSubmittedFileName(Part part) {
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

  private static String guessExtension(String submittedName, String contentType) {
    String lower = (submittedName == null ? "" : submittedName.toLowerCase(Locale.ROOT));
    if (lower.endsWith(".png")) return ".png";
    if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return ".jpg";
    if (lower.endsWith(".webp")) return ".webp";
    if (lower.endsWith(".gif")) return ".gif";
    if ("image/png".equals(contentType)) return ".png";
    if ("image/jpeg".equals(contentType)) return ".jpg";
    if ("image/webp".equals(contentType)) return ".webp";
    if ("image/gif".equals(contentType)) return ".gif";
    return ".png";
  }
}