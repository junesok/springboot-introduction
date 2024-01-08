package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository; //= new MemberRepository() 생략;

    //같은 리포지토리를 사용하기 위해 추가 - 외부에서 데이터를 가져오게 하기 위해서
    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름으로 회원 가입 불가능
        // - Optional<Member> result = memberRepository.findByName(member.getName());
        //result가 값이 있으면
        /*
           - result.ifPresent(m -> {

                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
        */

        //위 두 가지를 합치면
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        memberRepository.save(member);
        return member.getId();
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
