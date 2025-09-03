package gotit.auth;

public class AuthCode {
	    private AuthCode(){}
	    public static final int SUCCESS = 0;
	    public static final int NO_EMAIL = 1;
	    public static final int INVALID_PWD = 2;
	    public static final int SUSPENDED = 3;
	    public static final int DELETED = 4;
	    public static final int INVALID_NAME = 10;
	    public static final int INVALID_EMAIL = 11;
	    public static final int INVALID_PASSWORD = 12;
	    public static final int PASSWORD_MISMATCH = 13;
	    public static final int INVALID_ALIAS = 14;
	    public static final int TERMS_REQUIRED = 15;
	    public static final int DUPLICATE_EMAIL = 20;
	    public static final int DUPLICATE_ALIAS = 21;
	    public static final int DB_ERROR = 30;
}
