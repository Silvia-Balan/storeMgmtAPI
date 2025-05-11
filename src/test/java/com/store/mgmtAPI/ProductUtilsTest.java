package com.store.mgmtAPI;

import com.store.mgmtAPI.utils.CamelCaseDisplayNameGenerator;
import com.store.mgmtAPI.utils.ProductUtils;
import org.junit.jupiter.api.*;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(CamelCaseDisplayNameGenerator.class)
public class ProductUtilsTest {
    ProductUtils productUtils;

    @BeforeEach // create object from scratch before each test
    void setProductUtils(){
        productUtils = new ProductUtils();
    }
    @Test
    void testAdd(){
        System.out.printf("""
                Running test: %s
                """, productUtils.getMethodName());

        assertEquals(10, productUtils.add(4,6), "4+6 must be 10");
        assertNotEquals(5, productUtils.add(4,6), "4+6 must not be 5");
    }

    @Test
    @DisplayName("check if an object is null")
    void testCheckNull(){
        String var1 = null;
        var var2 = "testString";

        System.out.printf("""
                Running test: %s
                """, productUtils.getMethodName());

        assertNull(productUtils.checkNull(var1), "Object should be null");
        assertNotNull(productUtils.checkNull(var2), "Object should not be null");
    }

    @Test
    void testIsGreater(){
        var a = 5;
        var b = -10;

        System.out.printf("""
                Running test: %s
                """, productUtils.getMethodName());

        assertTrue(productUtils.isGreater(a, b), "This should return true");
        assertFalse(productUtils.isGreater(b, a), "This should return false");
    }
}
