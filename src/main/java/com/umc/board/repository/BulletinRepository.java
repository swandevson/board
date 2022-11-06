package com.umc.board.repository;

import com.umc.board.model.Bulletin;

import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BulletinRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BulletinRowMapper bulletinRowMapper;

    public BulletinRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.bulletinRowMapper = new BulletinRowMapper();
    }

    public List<Bulletin> findList() {

        return namedParameterJdbcTemplate.query(BulletinSql.SELECT, EmptySqlParameterSource.INSTANCE, this.bulletinRowMapper);
    }

    public Bulletin findBulletin(Integer idx) {
        String query = BulletinSql.SELECT + BulletinSql.IDX + ";";

        SqlParameterSource parameterSource = new MapSqlParameterSource("idx", idx);

        return namedParameterJdbcTemplate.query(query, parameterSource, this.bulletinRowMapper).get(0);
    }

    public List<Bulletin> findBulletinByWriter(String writer) {
        String query = BulletinSql.SELECT + BulletinSql.WRITER + ";";

        SqlParameterSource parameterSource = new MapSqlParameterSource("writer", writer);

        return namedParameterJdbcTemplate.query(query, parameterSource, this.bulletinRowMapper);
    }

    public Bulletin write(Bulletin bulletin) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource parameterSource = new MapSqlParameterSource("title", bulletin.title)
                .addValue("content", bulletin.content)
                .addValue("writer", bulletin.writer);

        int affectedRow = namedParameterJdbcTemplate.update(BulletinSql.INSERT, parameterSource, keyHolder);

        bulletin.idx = keyHolder.getKey().intValue();

        return bulletin;
    }

    public Integer update(Bulletin bulletin) {
        String query = BulletinSql.UPDATE + BulletinSql.IDX + ";";

        SqlParameterSource parameterSource = new MapSqlParameterSource("idx", bulletin.idx)
                .addValue("content", bulletin.content)
                .addValue("title", bulletin.title);

        return namedParameterJdbcTemplate.update(query, parameterSource);
    }

    public Integer delete(Integer idx) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("idx", idx);

        return namedParameterJdbcTemplate.update(BulletinSql.DELETE + BulletinSql.IDX, parameterSource);
    }
}
