package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// memberRepo에서 사용자 정보를 검색
		Member member = memberRepo.findById(username)
									.orElseThrow(()->new UsernameNotFoundException("Not Found"));
		System.out.println(member);	// 검색된 사용자 정보를 console에 출력해서 확인
		
		// UserDetails 타입의 객체를 생성해서 리턴
		return new User(member.getUsername(), member.getPassword(),
					AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
}
