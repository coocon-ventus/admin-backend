package com.coocon.admin.member;


import static org.assertj.core.api.Assertions.assertThat;

import com.coocon.admin.auth.oauth.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@DataJpaTest
//@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    private Member member1;

    @BeforeEach
    public void beforeEach(){
        member1 = Member.builder()
                .userId("defaultId")
                .name("default")
                .email("default@coocon.net")
                .password("1243")
                .profileImage("")
                .provider(Provider.COOCON)
                .build();
        memberRepository.save(member1);
    }

    @AfterEach
    public void afterEach(){

    }

    @Test
    void insertMember(){
        //Given
        Member member = Member.builder()
                .userId("testId1")
                .name("test1")
                .email("test1@coocon.net")
                .password("testtset")
                .profileImage("proimage")
                .provider(Provider.COOCON)
                .build();
        //When
        memberRepository.save(member);
        //Then
        Member resultMember=memberRepository.findByUserId("testId1").get();
        System.out.println(resultMember);
        System.out.println(member1);
        System.out.println(memberRepository.findAll());
        assertThat(resultMember).isEqualTo(member);

    }

    @Test
    void updateMember(){
        //given
        Member existMember=memberRepository.findByUserId("defaultId").get();
        //when
        existMember.setName("updated");
        existMember.setEmail("updated");
        memberRepository.save(existMember);
        //then
        System.out.println(memberRepository.findAll());
        assertThat(existMember);
    }

    @Test
    void deleteMember(){
        //given
        Member existMember=memberRepository.findByUserId("defaultId").get();
        //when
        memberRepository.delete(existMember);
        //then
        System.out.println("############");
        System.out.println(memberRepository.findAll());
    }



}
