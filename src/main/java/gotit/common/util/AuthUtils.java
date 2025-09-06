package gotit.common.util;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class AuthUtils {
	private static final int NAME_MIN  = 2;
	private static final int ALIAS_MIN = 2;
	private static final int ALIAS_MAX = 8;
	private static final int PASS_MIN  = 8;
	private static final Pattern ALIAS_RE = Pattern.compile("^[가-힣A-Za-z0-9_]+$");
	private static final Pattern EMAIL_RE = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$");

    private AuthUtils() {}

    public static String validateSignup(String name, String alias, String email,
                                        String password, String passwordConfirm,
                                        boolean agreeTerms) {
        name = trim(name);
        alias = trim(alias);
        email = trim(email);

        if (isBlank(name)) return "이름을 입력해주세요.";
        if (name.length() < NAME_MIN) return "이름은 최소 " + NAME_MIN + "자 이상이어야 합니다.";

        if (isBlank(alias)) return "닉네임을 입력해주세요.";
        if (alias.length() < ALIAS_MIN || alias.length() > ALIAS_MAX)
            return "닉네임은 " + ALIAS_MIN + "~" + ALIAS_MAX + "자여야 합니다.";
        if (!ALIAS_RE.matcher(alias).matches())
            return "닉네임은 한글/영문/숫자/밑줄(_)만 가능합니다.";

        if (isBlank(email)) return "이메일을 입력해주세요.";
        if (!EMAIL_RE.matcher(email).matches()) return "올바른 이메일 형식이 아닙니다.";

        if (isBlank(password)) return "비밀번호를 입력해주세요.";
        if (password.length() < PASS_MIN) return "비밀번호는 최소 " + PASS_MIN + "자 이상이어야 합니다.";
        if (isBlank(passwordConfirm)) return "비밀번호 확인을 입력해주세요.";
        if (!password.equals(passwordConfirm)) return "비밀번호가 일치하지 않습니다.";

        if (!agreeTerms) return "약관에 동의해야 회원가입이 가능합니다.";
        return null; // OK
	}

	public static String trim(String s) {
	    return s == null ? "" : s.trim();
	}
	public static boolean isBlank(String s) {
	    return trim(s).isEmpty();
	}

	public static String hash(String raw) {
	    return BCrypt.hashpw(raw, BCrypt.gensalt(12));
	}
	public static boolean matches(String raw, String hashed) {
	    return BCrypt.checkpw(raw, hashed);
	}
}
