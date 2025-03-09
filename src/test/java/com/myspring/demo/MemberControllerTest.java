package com.myspring.demo;

import com.myspring.demo.controller.MemberController;
import com.myspring.demo.data.MemberRepository;
import com.myspring.demo.model.Member;
import com.myspring.demo.service.MemberService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MemberService memberService;

	@MockBean
	private MemberRepository memberRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Member testMember;

	@BeforeEach
	void setUp() {
		testMember = new Member("1", "Alice", "alice@example.com", "1234567890");
	}

	@Test
	void testGetAllMembers() throws Exception {
		when(memberService.getAllMembers()).thenReturn(List.of(testMember));

		mockMvc.perform(get("/members"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Alice"))
				.andExpect(jsonPath("$[0].email").value("alice@example.com"))
				.andExpect(jsonPath("$[0].phone").value("1234567890"));
	}

	@Test
	void testLookupMemberById_Found() throws Exception {
		when(memberService.getMemberById("1")).thenReturn(Optional.of(testMember));

		mockMvc.perform(get("/members/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Alice"))
				.andExpect(jsonPath("$.email").value("alice@example.com"))
				.andExpect(jsonPath("$.phone").value("1234567890"));
	}

	@Test
	void testLookupMemberById_NotFound() throws Exception {
		when(memberService.getMemberById("2")).thenReturn(Optional.empty());

		mockMvc.perform(get("/members/2"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testCreateMember() throws Exception {
		when(memberService.createMember(any(Member.class))).thenReturn(testMember);

		mockMvc.perform(post("/members")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(testMember)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Alice"))
				.andExpect(jsonPath("$.email").value("alice@example.com"))
				.andExpect(jsonPath("$.phone").value("1234567890"));
	}
}
