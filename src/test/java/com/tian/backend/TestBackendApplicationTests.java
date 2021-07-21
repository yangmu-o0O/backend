package com.tian.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author muyang.tian
 * @date 2021/5/28 18:21
 */
@SpringBootTest
public class TestBackendApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(ThreadLocalRandom.current().ints(1, 1, 1 + 1).toArray()));
    }
}
