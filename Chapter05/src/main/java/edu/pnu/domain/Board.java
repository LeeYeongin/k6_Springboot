package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity		// table을 참조하여 알아서 생성(primary key 지정 필수)
public class Board {
	@Id		// primary key field임을 알림
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// auto increment라는 의미
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default
	@Temporal(value = TemporalType.TIMESTAMP)	// datatype지정(이 코드에서는 timestamp)
	private Date createDate = new Date();
	@Builder.Default
	private Long cnt=0L;
	
	public void update(Board board) {
		if(board.getTitle() != null) title = board.getTitle();
		if(board.getWriter() != null) writer = board.getWriter();
		if(board.getContent() != null) content = board.getContent();
	}
}
