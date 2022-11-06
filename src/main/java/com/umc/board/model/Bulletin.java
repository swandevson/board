package com.umc.board.model;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idx;
    public String title;
    public String content;
    public String writer;
}
