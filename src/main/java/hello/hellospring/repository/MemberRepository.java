package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //저장소에 회원 저장
    Member save(Member member);
    //ID로 찾아오기
    Optional<Member> findById(Long id);
    //이름으로 찾아오기
    Optional<Member> findBuName(String name);
    //저장되어 있는 모든 회원리스트 불러오기
    List<Member> findAll();
}
