package gotit.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate; // java.sql.Date를 LocalDate로 변환하기 위해 추가
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    /**
     * java.sql.Date를 인자로 받는 오버로딩된 메서드
     * 이 메서드는 sql.Date를 LocalDate -> LocalDateTime으로 변환합니다.
     */
    public static String formatTimeAgo(Date sqlDate) {
        if (sqlDate == null) {
            return "";
        }
        // 1. java.sql.Date를 LocalDate로 변환
        LocalDate localDate = sqlDate.toLocalDate();
        
        // 2. LocalDate에 시간 정보 (00:00:00)를 추가하여 LocalDateTime으로 변환
        LocalDateTime updatedTime = localDate.atStartOfDay();
        
        // 3. 기존 로직에 위임
        return formatTimeAgo(updatedTime);
    }
    
    /**
     * LocalDateTime 객체를 현재 시간 기준으로 "방금 전", "몇 분 전" 등으로 변환합니다.
     */
    public static String formatTimeAgo(LocalDateTime updatedTime) {
        if (updatedTime == null) {
            return "";
        }
        LocalDateTime now = LocalDateTime.now();
        long diffSeconds = ChronoUnit.SECONDS.between(updatedTime, now);
        
        if (diffSeconds < 60) {
            return "방금 전";
        } else if (diffSeconds < 3600) {
            return (diffSeconds / 60) + "분 전";
        } else if (diffSeconds < 86400) {
            return (diffSeconds / 3600) + "시간 전";
        } else if (diffSeconds < 604800) {
            return (diffSeconds / 86400) + "일 전";
        } else if (diffSeconds < 2592000) {
            long weeks = diffSeconds / 604800;
            return weeks + "주 전";
        } else if (diffSeconds < 31536000) {
            long months = diffSeconds / 2592000;
            return months + "개월 전";
        } else {
            long years = diffSeconds / 31536000;
            return years + "년 전";
        }
    }
    
    public static String formatTimeAgo(Timestamp ts) {
        if (ts == null) return "";
        return formatTimeAgo(ts.toLocalDateTime());
    }

}