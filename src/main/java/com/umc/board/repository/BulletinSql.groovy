package com.umc.board.repository;

class BulletinSql {
    public static final String SELECT = """
            SELECT idx, title, content, writer FROM bulletin WHERE 1=1""";

    public static final String IDX = """
            AND idx = :idx""";

    public static final String WRITER = """
            AND writer = :writer""";

    public static final String INSERT = """
            INSERT INTO bulletin(content, title, writer) VALUES(:content, :title, :writer)""";

    public static final String UPDATE = """
            UPDATE bulletin SET content = :content, title = :title WHERE 1=1""";

    public static final String DELETE = """
            DELETE FROM bulletin WHERE 1=1""";
}