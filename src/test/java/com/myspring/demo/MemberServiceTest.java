package com.myspring.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;

import com.myspring.demo.data.MemberRepository;
import com.myspring.demo.model.Member;
import com.myspring.demo.service.MemberService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllMembers() {
		when(memberRepository.findAll()).thenReturn(List.of(new Member("1", "Alice", "alice@example.com", "12345")));
		List<Member> members = memberService.getAllMembers();
		assertEquals(1, members.size());
	}

	@Test
	void testGetMemberById() {
		Member member = new Member("1", "Alice", "alice@example.com", "12345");
		when(memberRepository.findById("1")).thenReturn(Optional.of(member));

		Optional<Member> result = memberService.getMemberById("1");
		assertTrue(result.isPresent());
		assertEquals("Alice", result.get().getName());
	}

	@Test
	void testCreateMember() {
		Member member = new Member("2", "Bob", "bob@example.com", "6789076543");
		when(memberRepository.save(any(Member.class))).thenReturn(member);

		Member savedMember = memberService.createMember(member);
		assertNotNull(savedMember);
		assertEquals("Bob", savedMember.getName());
	}
}

