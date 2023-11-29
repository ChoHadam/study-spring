package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        String member_name = "spring";

        // when
        Long savedId = memberService.join(member_name);

        // then
        Member findMember = memberService.findMember(savedId).get();
        assertThat(member_name).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원가입_예외() {
        //given
        String member1_name = "spring";
        String member2_name = "spring";

        // when
        memberService.join(member1_name);
        Exception e = assertThrows(IllegalStateException.class, ()->memberService.join(member2_name));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}