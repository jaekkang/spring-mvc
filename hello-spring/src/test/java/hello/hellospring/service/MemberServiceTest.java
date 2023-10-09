package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    @DisplayName("회원가입")
    void sign_up() {
       //given
        Member member = new Member();
        member.setName("hello");

       //when
        Long saveId = memberService.join(member);


       //then

        Member member1 = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void validate_duplicate_members() {
        // given
            Member member1 = new Member();
            member1.setName("spring");

            Member member2 = new Member();
            member2.setName("spring");
        // when
            memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    @DisplayName("회원들 찾기")
    void findMembers() {
    }

    @Test
    @DisplayName("한명 찾기")
    void findOne() {
    }
}