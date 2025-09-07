// AvatarController.java
package gotit.file;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletContext;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@WebServlet("/avatar") // *.do 충돌 피하려고 /avatar 사용 권장
public class FileController extends HttpServlet {

  private Path explodedDir; // WAR가 exploded일 때의 실제 경로 (없을 수도 있음)

  @Override
  public void init() {
    // WAR가 exploded인 경우에만 실제 파일 경로가 나옵니다. (미배포 형태면 null 가능)
    String real = getServletContext().getRealPath("/WEB-INF/user-img");
    if (real != null) {
      explodedDir = Path.of(real).toAbsolutePath().normalize();
      System.out.println("[avatar] BASE_DIR=" + explodedDir);
    } else {
      System.out.println("[avatar] running in non-exploded mode; will use getResourceAsStream()");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    ServletContext ctx = getServletContext();

    String img = req.getParameter("img");
    if (img == null || img.isBlank()) img = "default.png";

    // 파일명 정규화 (경로탈출 방지)
    img = java.net.URLDecoder.decode(img, StandardCharsets.UTF_8);
    img = img.replaceAll("[\\p{Cntrl}]", "");
    img = img.replaceAll("[/\\\\]", "_");
    img = Paths.get(img).getFileName().toString();

    String lower = img.toLowerCase(Locale.ROOT);
    if (!(lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")
       || lower.endsWith(".webp") || lower.endsWith(".gif"))) {
      // 확장자 누락 시 png로 보정 (원하면 제거 가능)
      img = img + ".png";
      lower = img.toLowerCase(Locale.ROOT);
    }

    // 1) exploded WAR이면 파일시스템에서 시도
    if (explodedDir != null) {
      Path target = explodedDir.resolve(img).normalize();
      if (!target.startsWith(explodedDir)) {
        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
      }
      if (Files.exists(target)) {
        writeFile(resp, ctx, target, lower);
        return;
      }
      // 없으면 default.png
      Path def = explodedDir.resolve("default.png").normalize();
      if (Files.exists(def)) {
        writeFile(resp, ctx, def, "default.png");
        return;
      }
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    // 2) non-exploded WAR: 리소스 스트림으로 제공
    String resourcePath = "/WEB-INF/user-img/" + img;
    try (InputStream in = ctx.getResourceAsStream(resourcePath)) {
      if (in != null) {
        writeStream(resp, ctx, in, lower);
        return;
      }
    }
    try (InputStream in = ctx.getResourceAsStream("/WEB-INF/user-img/default.png")) {
      if (in != null) {
        writeStream(resp, ctx, in, "default.png");
        return;
      }
    }
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }

  private void writeFile(HttpServletResponse resp, ServletContext ctx, Path file, String name) throws IOException {
    String mime = ctx.getMimeType(name);
    if (mime == null) mime = guessMime(name);
    resp.setContentType(mime);
    resp.setHeader("Cache-Control", "public, max-age=3600");
    resp.setDateHeader("Last-Modified", Files.getLastModifiedTime(file).toMillis());
    resp.setHeader("X-Content-Type-Options", "nosniff");
    resp.setContentLengthLong(Files.size(file));
    try (OutputStream out = resp.getOutputStream()) {
      Files.copy(file, out);
    }
  }

  private void writeStream(HttpServletResponse resp, ServletContext ctx, InputStream in, String name) throws IOException {
    String mime = ctx.getMimeType(name);
    if (mime == null) mime = guessMime(name);
    resp.setContentType(mime);
    resp.setHeader("Cache-Control", "public, max-age=3600");
    resp.setHeader("X-Content-Type-Options", "nosniff");
    try (OutputStream out = resp.getOutputStream()) {
      in.transferTo(out);
    }
  }

  private String guessMime(String name) {
    String n = name.toLowerCase(Locale.ROOT);
    if (n.endsWith(".png"))  return "image/png";
    if (n.endsWith(".jpg") || n.endsWith(".jpeg")) return "image/jpeg";
    if (n.endsWith(".webp")) return "image/webp";
    if (n.endsWith(".gif"))  return "image/gif";
    return "application/octet-stream";
  }
}