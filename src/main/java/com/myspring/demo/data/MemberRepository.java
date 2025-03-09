package com.myspring.demo.data;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myspring.demo.model.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
	Optional<Member> findByName(String name);
	Optional<Member> findByEmail(String email);
	Optional<Member> findByPhone(String phone);
}
