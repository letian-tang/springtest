package com.zhoupu.dy.javatuples;

import java.util.Map;

import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.javatuples.*;
import org.junit.Test;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TupleTests {

    @Test
    public void tuplesTest() {
        // 1元组
        Unit<String> u = new Unit<String>("rensanning.iteye.com");
        log.info("{}", u.getValue(0));

        Pair<String, Integer> p = Pair.with("rensanning.iteye.com", 9527);
        log.info("p1={},p2={}", p.getValue0(), p.getValue1());

        Triplet<String, Integer, Double> triplet = Triplet.with("rensanning.iteye.com", 9527, 1.0);
        log.info("p1={},p2={},p3={}", triplet.getValue0(), triplet.getValue1(),
                triplet.getValue2());

        KeyValue<String, String> kv = KeyValue.with("rensanning.iteye.com", "9527");
        log.info("p1={},p2={}", kv.getKey(), kv.getValue());

        LabelValue<String, String> lv = LabelValue.with("rensanning.iteye.com", "9527");
        log.info("p1={},p2={}", lv.getLabel(), lv.getValue());
    }

    @Test
    public void pairTest() {
        Map.Entry<String, Integer> entry2 = Maps.immutableEntry("rensanning.iteye.com", 9527);
        Map.Entry<String, Integer> entry3 = new DefaultMapEntry("rensanning.iteye.com", 9527);
        org.apache.commons.collections4.KeyValue<String, Integer> kv =
                new DefaultKeyValue("rensanning.iteye.com", 9527);

        Map.Entry<String, Integer> entry4 =
                new ImmutablePair<String, Integer>("rensanning.iteye.com", 9527);

    }
}
