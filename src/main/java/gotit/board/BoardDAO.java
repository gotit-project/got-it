// gotit.board.BoardDAO.java
package gotit.board;

import gotit.model.Board;
import gotit.model.BoardType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private static final BoardDAO INSTANCE = new BoardDAO();
    private DataSource ds;

    private BoardDAO() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/gotDB");
        } catch (NamingException e) {
            throw new RuntimeException("JNDI DataSource lookup failed: jdbc/gotDB", e);
        }
    }

    public static BoardDAO getInstance() { return INSTANCE; }

    // 공통 select 컬럼(boards + board_types)
    private static final String BASE_SELECT = """
        SELECT
          b.board_id, b.name, b.description, b.is_public, b.post_count, b.last_post_at,
          b.created_at, b.updated_at,
          t.board_type_id, t.code AS type_code, t.name AS type_name, t.description AS type_desc
        FROM boards b
        JOIN board_types t ON b.board_type_id = t.board_type_id
        """;

    public List<Board> findAll() throws SQLException {
        String sql = BASE_SELECT + " ORDER BY b.board_id DESC";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Board> list = new ArrayList<>();
            while (rs.next()) list.add(mapRow(rs));
            return list;
        }
    }

    public List<Board> findByTypeCode(String typeCode) throws SQLException {
        String sql = BASE_SELECT + " WHERE t.code = ? ORDER BY b.board_id DESC";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, typeCode);
            try (ResultSet rs = ps.executeQuery()) {
                List<Board> list = new ArrayList<>();
                while (rs.next()) list.add(mapRow(rs));
                return list;
            }
        }
    }

    public Board findById(long boardId) throws SQLException {
        String sql = BASE_SELECT + " WHERE b.board_id = ?";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, boardId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    private Board mapRow(ResultSet rs) throws SQLException {
        BoardType t = new BoardType();
        t.setBoardTypeId(rs.getByte("board_type_id"));
        t.setCode(rs.getString("type_code"));
        t.setName(rs.getString("type_name"));
        t.setDescription(rs.getString("type_desc"));

        Board b = new Board();
        b.setBoardId(rs.getLong("board_id"));
        b.setBoardType(t);
        b.setName(rs.getString("name"));
        b.setDescription(rs.getString("description"));
        b.setPublic(rs.getBoolean("is_public"));
        b.setPostCount(rs.getInt("post_count"));

        Timestamp last = rs.getTimestamp("last_post_at");
        if (last != null) b.setLastPostAt(last.toLocalDateTime());

        b.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        b.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return b;
    }
}