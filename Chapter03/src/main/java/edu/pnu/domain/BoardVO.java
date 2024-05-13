package edu.pnu.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private int seq;
	private String title;
	private String wirter;
	private String content;
	private Date creatDate = new Date();
	private int cnt;
}
