package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
