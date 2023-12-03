package hello.hellospring.controller;

import hello.hellospring.dto.CreateMemberDto;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(CreateMemberDto createMemberDto) {
        memberService.join(createMemberDto.getName());

        return "redirect:/";
    }
}
