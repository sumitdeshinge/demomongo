package com.myspring.demo;

import java.util.Optional;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.myspring.demo.data.MemberRepository;
import com.myspring.demo.model.Member;
import static org.junit.jupiter.api.Assertions.*;

// this can be tested once we have docker environment setup

@Disabled
@DataMongoTest
@Testcontainers
class MemberRepositoryTest {
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		mongoDBContainer.start();
		memberRepository.deleteAll();
	}

	@AfterEach
	void tearDown() {
		mongoDBContainer.stop();
	}

	@Test
	void testSaveAndFindMember() {
		Member member = new Member(null, "Charlie", "charlie@example.com", "11111");
		member = memberRepository.save(member);

		assertNotNull(member.getId());
		Optional<Member> found = memberRepository.findById(member.getId());
		assertTrue(found.isPresent());
		assertEquals("Charlie", found.get().getName());
	}

	@Test
	void testFindByEmail() {
		Member member = new Member(null, "David", "david@example.com", "22222");
		memberRepository.save(member);

		Optional<Member> found = memberRepository.findByEmail("david@example.com");
		assertTrue(found.isPresent());
		assertEquals("David", found.get().getName());
	}
}

