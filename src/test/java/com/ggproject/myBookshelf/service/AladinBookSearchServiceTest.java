//package com.ggproject.myBookshelf.service;
//
//
//import com.ggproject.myBookshelf.dto.UsedBookSearchResponseDto;
//import com.ggproject.myBookshelf.service.api.AladinBookSerachService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//@RestClientTest(value = AladinBookSerachService.class)
//public class AladinBookSearchServiceTest {
//
//    @Autowired
//    private AladinBookSerachService service;
//
//    @Autowired
//    private MockRestServiceServer mockRestServiceServer;
//
//    @Test
//    public void 중고검색API를_호출했을때_UsedBookSearchResponseDto에_결과를_담을수있다() {
//
//        //given
//        String expectResult = "{\"link\":\"http://www.aladin.co.kr/search/wsearchresult.aspx?SearchTarget=UsedStore&SearchWord=8960773417&partner=openAPI\",\"pubDate\":\"Wed, 13 May 2020 01:41:20 GMT\",\"query\":\"isbn13=9788960773417\",\"version\":\"20131101\",\"itemOffStoreList\":[{\"offCode\":\"NowonStn\",\"offName\":\"노원역점\",\"link\":\"http://www.aladin.co.kr/usedstore/wproduct.aspx?ItemId=19505561&OffCode=NowonStn&partner=openAPI\"},{\"offCode\":\"snue\",\"offName\":\"서울대입구역점\",\"link\":\"http://www.aladin.co.kr/usedstore/wproduct.aspx?ItemId=19505561&OffCode=snue&partner=openAPI\"}] }";
//        String apiUrl = "http://www.aladin.co.kr/ttb/api/ItemOffStoreList.aspx?ttbkey=ttbgo13231040001&itemIdType=ISBN13&ItemId=9788960773417&output=js";
//
//        mockRestServiceServer.expect(requestTo(apiUrl)).andRespond(withSuccess(expectResult, MediaType.APPLICATION_JSON));
//
//        //when
//        UsedBookSearchResponseDto response = service.getBookInformations("9788960773417");
//
//        //then
//        assertThat(response.getLink()).isNotEmpty();
//    }
//}