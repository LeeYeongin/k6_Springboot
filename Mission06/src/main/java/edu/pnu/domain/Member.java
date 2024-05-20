package edu.pnu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id
	private String username;
	private String password;
	private String role;
	
	public void update(Member member) {
		if(member.getPassword() != null)
			this.password = member.getPassword();
		if(member.getRole() != null)
			this.role = member.getRole();
	}
}
