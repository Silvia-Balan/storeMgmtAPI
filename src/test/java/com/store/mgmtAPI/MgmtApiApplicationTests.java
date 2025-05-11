package com.store.mgmtAPI;

import com.store.mgmtAPI.utils.CamelCaseDisplayNameGenerator;
import com.store.mgmtAPI.utils.ProductUtils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(CamelCaseDisplayNameGenerator.class)
class MgmtApiApplicationTests {

	@Test
	void contextLoads() {
		System.out.printf("""
                Running test: %s
                """, ProductUtils.getMethodName());
	}

}
