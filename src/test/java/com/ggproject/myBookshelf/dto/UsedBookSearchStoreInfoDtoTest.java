package com.ggproject.myBookshelf.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsedBookSearchStoreInfoDtoTest {

    @Test
    public void URL_특수문자_제거() {

        // given
        String url = "http://www.aladin.co.kr/usedstore/wproduct.aspx?ItemId=19505671&amp;OffCode=Daegu&amp;partner=openAPI";
        UsedBookSearchStoreInfoDto storeInfoDto = new UsedBookSearchStoreInfoDto();

        // when
        storeInfoDto.setLink(url);

        // then
        String expectedUrl = "http://www.aladin.co.kr/usedstore/wproduct.aspx?ItemId=19505671&OffCode=Daegu&partner=openAPI";
        assertThat(storeInfoDto.getLink()).isEqualTo(expectedUrl);
    }
}