package com.myspring.demo;

import org.junit.jupiter.api.Test;

import com.myspring.demo.model.Member;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
	@Test
	void testMemberCreation() {
		Member member = new Member();
		member.setId("1");
		member.setName("John Doe");
		member.setEmail("john@example.com");
		member.setPhone("1234567890");

		assertEquals("1", member.getId());
		assertEquals("John Doe", member.getName());
		assertEquals("john@example.com", member.getEmail());
		assertEquals("1234567890", member.getPhone());
	}
}
