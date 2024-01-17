package com.example.sparringday.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtils {

    /**
     * Pageable 객체 생성
     * @param page 페이지. (최소 0, Default 0)
     * @param pageSize 페이지 당 컨텐츠 갯수. (최소 1, Default 10)
     * @return Pageable 객체
     */
    public static Pageable createPageable(Integer page, Integer pageSize) {
        int defaultPage = 0;
        int defaultPageSize = 10;

        int validPage = (CustomObjectUtils.isNotNull(page) && page >= 0) ? page : defaultPage;
        int validPageSize = (CustomObjectUtils.isNotNull(pageSize) && pageSize >= 1) ? pageSize : defaultPageSize;

        return PageRequest.of(validPage, validPageSize);
    }
}