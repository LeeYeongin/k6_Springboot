package edu.pnu.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@ToString.Exclude	// board class를 toString하면 member도 toString 실행 -> 무한 반복 -> exclude를 사용하여 무한루프 제거
	@Builder.Default
	@OneToMany(mappedBy="member", 
				fetch=FetchType.EAGER,
				cascade=CascadeType.ALL)	// 영속성 전이 : 삭제할때 관련된 보드도 삭제
	private List<Board> boardList = new ArrayList<Board>();
}
