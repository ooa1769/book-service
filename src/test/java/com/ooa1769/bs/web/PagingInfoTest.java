package com.ooa1769.bs.web;

import org.junit.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PagingInfoTest {

    @Test
    public void 페이징_테스트() {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 58).forEach(i -> list.add(i));
        PageImpl<Integer> pageImpl = new PageImpl<>(list, new PageRequest(0, 10), list.size());
        PagingInfo pagingInfo = new PagingInfo(pageImpl);

    }
}
