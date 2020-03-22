package com.zhou.demoweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class DemoWebApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		//D ：天 T：天和小时之间的分隔符 H ：小时  M：分钟  S：秒 每个单位都必须是数字，且时分秒顺序不能乱
		Duration fromChar1 = Duration.parse("PT30M");
		System.out.println(fromChar1.getSeconds());
	}

}
