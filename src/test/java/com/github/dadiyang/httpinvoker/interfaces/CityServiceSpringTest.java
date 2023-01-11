package com.github.dadiyang.httpinvoker.interfaces;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.dadiyang.httpinvoker.TestApplication;
import com.github.dadiyang.httpinvoker.entity.City;
import com.github.dadiyang.httpinvoker.entity.DemoData;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.dadiyang.httpinvoker.util.CityUtil.createCity;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
public class CityServiceSpringTest {
    private static final int PORT = 18888;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(PORT));
    @Autowired
    private CityService cityService;

    @Test
    public void getCity() {
        System.out.println(cityService.toString());
        int id = 1;
        String uri = "/city/getById?id=" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .withCookie("testCookie", equalTo("OK"))
                .withHeader("Authorization", equalTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM3MDc5MDc4OTUsInBheWxvYWQiOiJ7XCJpZFwiOjksXCJ1c2VybmFtZVwiOlwi5rWL6K-VLeWImOS8iuWHoVwiLFwibG9naW5OYW1lXCI6XCIxMzU4NTUwNDM4M1wiLFwidXNlclR5cGVcIjoxLFwiZG9jdG9ySWRcIjoxODY5ODIsXCJyb29tSWRcIjoxODY5ODIsXCJ0b2tlblwiOlwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SmxlSEFpT2pFMk56TTNNRGM1TURjNE9UUXNJbkJoZVd4dllXUWlPaUo3WENKcFpGd2lPakU0TmprNE1uMGlmUS5DaVVjSEYwdDJtcE5qdHZaNTBvLXdsb19nbWhZMWkzaXRvbFBqOGg4WE5vXCJ9In0.MFEODwPwugRfqyV4oC1HqWCPPQQrq6P75x3iMK1IrC8"))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City city = cityService.getCity(id);
        assertEquals(mockCity, city);
    }

    @Test
    public void mock() {
        // 在 TestApplication 中添加了 mock 规则，因此这个请求不会被真正发出
        String name = cityService.getCityName(1);
        assertEquals("北京", name);
    }

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simplecsvRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "C:\\Users\\Lance\\Desktop\\sqlResult_3406031.csv";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, DemoData.class, new DemoExceptionListener()).sheet().doRead();

        // 写法1 JDK8+
        // since: 3.0.0-beta1
//        String fileNameOut = "C:\\Users\\Lance\\Desktop\\sqlResp.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileNameOut, DemoData.class).sheet("返回结果").doWrite(data());


    }
}
