package com.example.cookers.domain.member.repository;

import com.example.cookers.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByusername(String username);
    Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode);

    List<Member> findByemail(String email);
}