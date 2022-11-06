package com.umc.board.repository

import com.umc.board.model.Bulletin
import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class BulletinRowMapper implements RowMapper<Bulletin> {
    @Override
    public Bulletin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bulletin bulletin = new Bulletin();

        bulletin.idx = rs.getInt("idx");
        bulletin.title = rs.getString("title");
        bulletin.content = rs.getString("content");
        bulletin.writer = rs.getString("writer");

        return bulletin;
    }
}
