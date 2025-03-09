package com.myspring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.demo.service.MemberService;
import com.myspring.demo.model.Member;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/members")
public class MemberController {
	private final MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}

	@GetMapping
	public List<Member> getMembers() {
		return service.getAllMembers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> lookupMemberById(@PathVariable String id) {
		Optional<Member> member = service.getMemberById(id);
		return member.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Member> createMember(@RequestBody Member member) {
		return ResponseEntity.ok(service.createMember(member));
	}
}
