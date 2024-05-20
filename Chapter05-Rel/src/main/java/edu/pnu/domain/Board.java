package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity		// table을 참조하여 알아서 생성(primary key 지정 필수)
public class Board {
	@Id		// primary key field임을 알림
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// auto increment라는 의미
	private Long seq;
	private String title;
	private String content;
	@Builder.Default
	@Temporal(value = TemporalType.TIMESTAMP)	// datatype지정(이 코드에서는 timestamp)
	private Date createDate = new Date();
	@Builder.Default
	private Long cnt=0L;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	public void update(Board board) {
		if(board.getTitle() != null) title = board.getTitle();
		if(board.getContent() != null) content = board.getContent();
	}
}
