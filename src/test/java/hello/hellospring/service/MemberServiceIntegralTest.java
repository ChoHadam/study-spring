package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegralTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void 회원가입() throws Exception {
        //given
        String member_name = "test_member";

        // when
        Long savedId = memberService.join(member_name);

        // then
        Member findMember = memberRepository.findById(savedId).get();
        assertThat(member_name).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원가입_예외() throws Exception {
        //given
        String member1_name = "test_member";
        String member2_name = "test_member";

        // when
        memberService.join(member1_name);
        Exception e = assertThrows(IllegalStateException.class, ()->memberService.join(member2_name));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
