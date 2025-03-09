package com.myspring.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.myspring.demo.data.MemberRepository;
import com.myspring.demo.model.Member;
import jakarta.annotation.PreDestroy;

@Service
public class MemberService {
	private final MemberRepository repository;

	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	public List<Member> getAllMembers() {
		return repository.findAll();
	}

	public Optional<Member> getMemberById(String id) {
		return repository.findById(id);
	}

	public Member createMember(Member member) {
		validateMember(member);
		return repository.save(member);
	}

	private void validateMember(Member member) {
		if (member.getName() == null || member.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if (member.getEmail() == null || !member.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Invalid email format");
		}
		if (member.getPhone() == null || !member.getPhone().matches("\\d{10}")) {
			throw new IllegalArgumentException("Phone number must be 10 digits");
		}

		if (repository.findByName(member.getName()).isPresent()) {
			throw new IllegalArgumentException("A member with this name already exists");
		}
		if (repository.findByEmail(member.getEmail()).isPresent()) {
			throw new IllegalArgumentException("A member with this email already exists");
		}
		if (repository.findByPhone(member.getPhone()).isPresent()) {
			throw new IllegalArgumentException("A member with this phone number already exists");
		}
	}

	@EventListener
	public void onShutdown(ContextClosedEvent event) {
		System.out.println("Cleaning up MongoDB before shutdown...");
		repository.deleteAll();
	}
}
