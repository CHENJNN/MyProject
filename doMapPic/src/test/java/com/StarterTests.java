package com;

import com.jn.model.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarterTests {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    void contextLoads() {


    }

}
