package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메소드가 동작을 끝날 때마다 지정한 동작을 하게 함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        //이름 설정
        member.setName("크리스티아누 호날두");
        //리포지토리에 member 저장
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //test1
        //콘솔에 result = true라고 뜨면 테스트 성공
        //System.out.println("result = " + (member == result));

        //test2
        //import org.junit.jupiter.api.Assertions;
        //실행시 콘솔창에 따로 뜨는 내용은 없으나 초록색 체크 모양이 뜨면 테스트 성공
        //에러 발생시 Expected : ???(기대했던 값) Actual : ???(실제값) 표시됨
        //Assertions.assertEquals(member(비교값), result(실제값);
        //Assertions.assertEquals(member, result);

        //test3
        //import org.assertj.core.api.Assertions;
        //Assertions는 option + enter 키로 Add on-demand static import... 클릭해서 생략 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("리오넬 메시");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("파울로 디발라");
        repository.save(member2);

        //리포지토리에서 이름이 리오넬 메시인 결과 찾기
        Member result = repository.findByName("리오넬 메시").get();

        //파울로 디발라를 저장한 member2에 결과값을 비교하니 오류 발생
        //assertThat(result).isEqualTo(member2);

        //member1에 비교하니 같은 리오넬 메시임으로 정상 작동
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("리오넬 메시");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("파울로 디발라");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //펩과 클롭 둘을 저장했기 때문에 기대값은 2
        assertThat(result.size()).isEqualTo(2);

        //MemoryMemberRepositoryTest로 실행시 오류 발생
        //findByName()에서 member1 & 2에 입력한 이름이 같기 때문에 에러 발생
        //따라소 메소드 하나가 동작을 마칠 때마다 afterEach()가 호출되게 해야 함.
    }
}
