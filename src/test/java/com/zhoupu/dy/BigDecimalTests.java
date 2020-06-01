package com.zhoupu.dy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 代码描述
 * <p>
 *
 * @author 阿汤
 * @since 2020/2/6 13:18
 */
@Slf4j
public class BigDecimalTests {

    @Test
    public void bigDecimalTest() {
        BigDecimal bigDecimal = new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000");
        BigDecimal addResult = bigDecimal.add(new BigDecimal("20"));
        BigDecimal divideResult = bigDecimal.divide(new BigDecimal("20"),BigDecimal.ROUND_UP);
        assertThat(addResult).isEqualTo("10000000000000000000000000000000000000000000000000000000000000020");
        assertThat(divideResult).isEqualTo("500000000000000000000000000000000000000000000000000000000000000");
    }
}
