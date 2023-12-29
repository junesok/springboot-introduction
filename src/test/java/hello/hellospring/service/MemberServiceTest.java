package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; //= new MemberService() 수정
    //store 비워버리는 로직이 있어서
    MemoryMemberRepository memberRepository; //= new MemoryMemberRepository() 수정
    //하지만 같은 리포지토리로 테스트해야 함
    //따라서 MemberService.java 수정(11~16 수정)

    //위를 수정하고 추가 - 각 테스트가 동작하기 전
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //메소드가 동작을 끝날 때마다 지정한 동작을 하게 함
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 특정 상황, 데이터
        Member member = new Member();
        member.setName("킬리안 음바페");
        //when 상황이 주어졌을 때
        Long saveId = memberService.join(member);
        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("킬리안 음바페");

        Member member2 = new Member();
        member2.setName("킬리안 음바페");
        //when
        memberService.join(member1);
        //memberService.join(member2) 로직을 실행 했을 때, IllegalStateException 예외가 발생해야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}