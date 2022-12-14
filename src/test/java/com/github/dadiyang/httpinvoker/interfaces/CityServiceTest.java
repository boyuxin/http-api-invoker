package com.github.dadiyang.httpinvoker.interfaces;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.dadiyang.httpinvoker.HttpApiProxyFactory;
import com.github.dadiyang.httpinvoker.entity.*;
import com.github.dadiyang.httpinvoker.entity.convert.ConsultSettingsConvert;
import com.github.dadiyang.httpinvoker.requestor.*;
import com.github.dadiyang.httpinvoker.util.CityUtil;
import com.github.dadiyang.httpinvoker.util.ParamUtils;
import com.github.dadiyang.httpinvoker.util.StringUtils;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import wiremock.com.google.common.collect.Lists;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.dadiyang.httpinvoker.util.CityUtil.createCities;
import static com.github.dadiyang.httpinvoker.util.CityUtil.createCity;
import static com.github.dadiyang.httpinvoker.util.IoUtils.closeStream;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*;
@Slf4j
@RunWith(Parameterized.class)
public class CityServiceTest {
    private static final int PORT = 18888;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(PORT));
    private CityService cityService;
    private CityService cityServiceWithResultBeanResponseProcessor;
    private Requestor requestor;
    private String authKey;

    public CityServiceTest(Requestor requestor) {
        this.requestor = requestor;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{new JsoupRequestor()});
        data.add(new Object[]{new DefaultHttpRequestor()});
        data.add(new Object[]{new HttpClientRequestor()});
        return data;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("api.url.city.host", "http://localhost:" + PORT);
        System.setProperty("api.url.city.host2", "http://localhost:" + PORT);

        HttpApiProxyFactory httpApiProxyFactory = new HttpApiProxyFactory.Builder().setRequestor(requestor).build();
        cityService = httpApiProxyFactory.getProxy(CityService.class);
        cityServiceWithResultBeanResponseProcessor = new HttpApiProxyFactory.Builder()
                .setRequestor(requestor)
                .setResponseProcessor(new ResultBeanResponseProcessor())
                .build()
                .getProxy(CityService.class);
        authKey = UUID.randomUUID().toString();
    }

    @Test
    public void testtttt(){
        HtmlToPdf htmlToPdf = HtmlToPdf.create();
        htmlToPdf.object(HtmlToPdfObject.forUrl("https://blog.csdn.net/weixin_49189242/article/details/124627556"));
        htmlToPdf.convert("C:\\Users\\Lance\\Desktop\\wor234234dfile.pdf");
    }


    @Test
    public void ??????(){
        Set<String> diagnoseIds = getDocIds();

        AtomicInteger i = new AtomicInteger();
        diagnoseIds.parallelStream().forEach(diagnoseId -> {
            i.getAndIncrement();
            System.err.println("================="+i+"================");
            try {
//                refundAndRepealWipedAssets(diagnoseId);
                CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
                closeConsultOrder.setDiagnoseId(diagnoseId);
                System.out.println("????????????"+JSON.toJSONString(closeConsultOrder));
                Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
                System.out.println("????????????"+JSON.toJSONString(result));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    @Test
    public void ???????????????(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("202301031032243614");
        strings.add("202301031055049298");
        strings.add("202301031142280958");
        strings.add("202301031719292948");
        strings.add("202301031723472087");
        strings.add("202301031727318518");
        strings.add("202301031750391816");
        strings.add("202301031824233697");
        strings.add("202301031830187380");
        strings.add("202301031838100270");
        strings.add("202301031855372632");
        strings.add("202301032137550925");
        strings.add("202301032220434474");
        strings.add("202301032247104303");
        for (String diagnoseId:strings) {
            BzsReqDTO bzsReqDTO = new BzsReqDTO();
            bzsReqDTO.setReqSystem("??????");
            bzsReqDTO.setDiagnoseId(diagnoseId);
            bzsReqDTO.setTraceId(UUID.randomUUID().toString());
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-FOSUN-TOKEN", "bDRLTEtha0lUUkdLVHJFMXczU3FHNE9Qa29NN0VqS2ZoSGFha0g2MHhFbVNqQ2VrTnlYTzFLVmRhSUpCKzN6OEdsaTd1UGwxaGEyYXlWQjBYMXpRS1dxdC8xT0VMWC9hWWVrcWNxamRUNCszUnZob3N3M2JNVi9LK0Q0M3F0L3EvMitwTFFBWForWTFQTGRWSzNJWFpmSlFhc292VzBXSVlKWUJ3RDY5cHAxdDhTVExLdnBXVzJNR3R2Vk5HcGdnczJ0WkxFMkpsYkJ3RWxjNDdEdVY0TFpNRU03TmVldGVGTERudlpHODFqWT0");
            Result<Boolean> result = cityService.bzsPush(bzsReqDTO,headers);
            System.out.println(result);
        }
    }

    /**
     *???????????? : ??????????????????
     * @author boyuxin
     * @date 2022/11/28 11:30
     * @return void
     */
    @Test
    public void clearDoctor(){

        Set<String> docIds = getDocIds();
//        Set<String> docIds = new HashSet<>();
        docIds.add("243507");
        //??????????????????
        int i = 0;
        for (String docId:docIds) {
            i++;
            System.err.println("================="+i+"================");
            QueryMultiConsultSettingsReqDTO queryMultiConsultSettingsReqDTO = new QueryMultiConsultSettingsReqDTO();
            queryMultiConsultSettingsReqDTO.setDoctorId(docId);
            queryMultiConsultSettingsReqDTO.setReqSystem("API-??????????????????");
            String traceId = UUID.randomUUID().toString();
            queryMultiConsultSettingsReqDTO.setTraceId(traceId);
            Result<List<QueryConsultSettingsResDTO>> listResult = cityService.queryConsultSettingsList(queryMultiConsultSettingsReqDTO);
            if (listResult== null || !listResult.isSuccess() ||  listResult.getResult() == null) {
                log.error("????????????:{}----{}",queryMultiConsultSettingsReqDTO,listResult);
                continue;
            }
            //???????????????
            Result<Boolean> delDocSetting = cityService.delDocSetting(queryMultiConsultSettingsReqDTO);
            log.info("????????????????????????:{}",delDocSetting);
            List<QueryConsultSettingsResDTO> result = listResult.getResult();
            for (QueryConsultSettingsResDTO resDTO : result) {
                String consultMode = resDTO.getConsultMode();
                if ("TELEPHONE".equals(consultMode)) {
                    log.info("????????????");
                    continue;
                }
                //??????????????????
                SubmitConsultSettingsReqDTO reqDTO = ConsultSettingsConvert.getSubmitConsultSettingsReqDTO(traceId, resDTO);
                log.info("????????????????????????:{}",JSON.toJSONString(reqDTO));
                Result<Boolean> submitConsultSettings = cityService.submitConsultSettings(reqDTO);
                log.info("??????????????????????????????:{}",submitConsultSettings);
            }
        }
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }



    private Set<String> getDocIds() {
        String fileName = "C:\\Users\\Lance\\Downloads\\?????????????????????0109.xlsx";
        Set<String> docIds = new HashSet<String>();
        EasyExcel.read(fileName, CleanDoctorDemoData.class, new PageReadListener<CleanDoctorDemoData>(dataList -> {
            for (CleanDoctorDemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDocId());
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();
        return docIds;
    }


    public static void main(String[] args) {
        Double twPrice = 0.0;
        BigDecimal bai = new BigDecimal(100);
        BigDecimal bigDecimal = BigDecimal.valueOf(twPrice);
        BigDecimal multiply = bigDecimal.multiply(bai);
        long l = multiply.longValue();
        long l1 = twPrice.longValue() * 100;
        System.out.println();
    }

    @Test
    public void testOne(){
        Result<Boolean> test = test("243507");
        log.info("????????????=={}",test);
        System.out.println(test);
    }


    public Result<Boolean> test(String docId) {
        if (StringUtils.isBlank(docId)) {
            return new Result<Boolean>(false);
        }
        MergeConsultSettingsReqDTO mergeConsultSettingsReqDTO = new MergeConsultSettingsReqDTO();
        HashSet<String> strings = new HashSet<String>();
        strings.add(docId);
        mergeConsultSettingsReqDTO.setDoctorIds(strings);
        System.out.println("?????????-????????????ID"+docId);
        Result<Boolean> result = cityService.getAllCities11(mergeConsultSettingsReqDTO);
//        String result = cityService.getAllCities11(mergeConsultSettingsReqDTO);
        System.out.println("?????????-????????????ID"+docId+"==??????????????????"+result);
        return result;
    }

    @Test
    public void closeConsultData() {
        // ??????1???JDK8+ ,?????????????????????DemoDataListener
        // since: 3.0.0-beta1
//        String fileName = "C:\\Users\\Lance\\Desktop\\????????????????????????.xlsx";
        String fileName = "C:\\Users\\Lance\\Desktop\\??????.xlsx";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet ????????????????????????
        // ?????????????????????100????????? ?????????????????? ??????????????????????????????
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDiagnoseId());
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();

        AtomicInteger i = new AtomicInteger(1);
        docIds.parallelStream().forEach(d -> {
            CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
            closeConsultOrder.setDiagnoseId(d);
            System.out.println("????????????"+JSON.toJSONString(closeConsultOrder));
            Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
            System.out.println("????????????"+JSON.toJSONString(result));
            System.out.println("================================"+ i.getAndIncrement());
        });
//        for (String d: docIds) {
//            CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
//            closeConsultOrder.setDiagnoseId(d);
//            System.out.println("????????????"+JSON.toJSONString(closeConsultOrder));
//            Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
//            System.out.println("????????????"+JSON.toJSONString(result));
//            System.out.println("================================"+ i.getAndIncrement());
////            try {
////                Thread.sleep(50);
////            } catch (InterruptedException e) {
////                System.out.println("??????");
////            }
//        }
        try {
            Thread.sleep(9999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void close(){
        CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
        closeConsultOrder.setDiagnoseId("202212151407099831");
        System.out.println("????????????"+JSON.toJSONString(closeConsultOrder));
        Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
        System.out.println("????????????"+JSON.toJSONString(result));
    }

    @Test
    public void ??????????????????(){

        String fileName = "C:\\Users\\Lance\\Downloads\\????????????????????????1 (13).xlsx";
        ArrayList<SubmitConsultSettingsRespDTO> submitConsultSettingsRespDTOS = new ArrayList<>();

        EasyExcel.read(fileName, SubmitConsultSettingsExcelData.class, new PageReadListener<SubmitConsultSettingsExcelData>(dataList -> {
            for (SubmitConsultSettingsExcelData demoData : dataList) {
                try {
                    SubmitConsultSettingsRespDTO result = updateSubmitConsultSettings(demoData);
                    submitConsultSettingsRespDTOS.add(result);
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();

        System.out.println(submitConsultSettingsRespDTOS);
        // ??????1
        String fileNameresp = "C:\\Users\\Lance\\Downloads\\????????????????????????1 (6)" + System.currentTimeMillis() + ".xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet?????????????????? ??????????????????????????????
        EasyExcel.write(fileNameresp, SubmitConsultSettingsRespDTO.class).sheet("??????").doWrite(submitConsultSettingsRespDTOS);
        try {
            Thread.sleep(99999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testregister(){
        String fileName = "C:\\Users\\Lance\\Desktop\\??????.xlsx";
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDiagnoseId());
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();

        docIds.parallelStream().forEach(user ->{
            UserRegisterReqDTO userRegisterReqDTO = new UserRegisterReqDTO();
            userRegisterReqDTO.setReqSystem("DEV");
            userRegisterReqDTO.setTraceId(UUID.randomUUID().toString());
            userRegisterReqDTO.setUserId(user);
            userRegisterReqDTO.setName(user);
            userRegisterReqDTO.setAvatarImgUrl(user);
            userRegisterReqDTO.setMasterCode("00000005");
            userRegisterReqDTO.setConsultSource("FOSUN_HEALTH");
            userRegisterReqDTO.setAccountType("INTERNAL");
            log.info("????????????:{}",userRegisterReqDTO);
            Result<UserRegisterResDTO> register = cityService.register(userRegisterReqDTO);
            log.info("????????????:{}",register);

        } );
//        for (String user:docIds) {
//            UserRegisterReqDTO userRegisterReqDTO = new UserRegisterReqDTO();
//            userRegisterReqDTO.setReqSystem("DEV");
//            userRegisterReqDTO.setTraceId(UUID.randomUUID().toString());
//            userRegisterReqDTO.setUserId(user);
//            userRegisterReqDTO.setName(user);
//            userRegisterReqDTO.setAvatarImgUrl(user);
//            userRegisterReqDTO.setMasterCode("00000005");
//            userRegisterReqDTO.setConsultSource("FOSUN_HEALTH");
//            userRegisterReqDTO.setAccountType("INTERNAL");
//            Result<UserRegisterResDTO> register = cityService.register(userRegisterReqDTO);
//        }
        System.out.println("");

    }
    public void refundAndRepealWipedAssets(String diagnoseId){
        RefundReqDTO refundReqDTO = new RefundReqDTO();
        refundReqDTO.setDiagnoseId(diagnoseId);
        log.info("??????????????????:{}",refundReqDTO);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM3MDc5MDc4OTUsInBheWxvYWQiOiJ7XCJpZFwiOjksXCJ1c2VybmFtZVwiOlwi5rWL6K-VLeWImOS8iuWHoVwiLFwibG9naW5OYW1lXCI6XCIxMzU4NTUwNDM4M1wiLFwidXNlclR5cGVcIjoxLFwiZG9jdG9ySWRcIjoxODY5ODIsXCJyb29tSWRcIjoxODY5ODIsXCJ0b2tlblwiOlwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SmxlSEFpT2pFMk56TTNNRGM1TURjNE9UUXNJbkJoZVd4dllXUWlPaUo3WENKcFpGd2lPakU0TmprNE1uMGlmUS5DaVVjSEYwdDJtcE5qdHZaNTBvLXdsb19nbWhZMWkzaXRvbFBqOGg4WE5vXCJ9In0.MFEODwPwugRfqyV4oC1HqWCPPQQrq6P75x3iMK1IrC8");
        String s = cityService.refundAndRepealWipedAssets(refundReqDTO,headers);
        log.info("??????????????????:{}",s);
    }

    private SubmitConsultSettingsRespDTO updateSubmitConsultSettings(SubmitConsultSettingsExcelData demoData) {
        SubmitConsultSettingsRespDTO submitConsultSettingsRespDTO = new SubmitConsultSettingsRespDTO();
        try {
            SubmitConsultSettingsReqDTO reqDTO = convertConsultSettings(demoData);
            log.info("????????????????????????????????????:{}",reqDTO);
            Result<Boolean> result = cityService.submitConsultSettings(reqDTO);
            log.info("????????????????????????????????????:{}",result);
            if (result.isSuccess()) {
                submitConsultSettingsRespDTO.setRemark("??????");
            }else {
                submitConsultSettingsRespDTO.setRemark("??????");
            }
            submitConsultSettingsRespDTO.setDoctorId(reqDTO.getDoctorId());
            submitConsultSettingsRespDTO.setMasterCode(reqDTO.getMasterCode());
            submitConsultSettingsRespDTO.setConsultMode(reqDTO.getConsultMode());
            submitConsultSettingsRespDTO.setSettingsSwitch(reqDTO.getSettingsSwitch());
            submitConsultSettingsRespDTO.setServicePrice(reqDTO.getServicePrice());
            submitConsultSettingsRespDTO.setChargebackTime(reqDTO.getChargebackTime());
            submitConsultSettingsRespDTO.setConsultSettingsNo(result.getCallId());
        } catch (Exception e) {
            e.printStackTrace();
            submitConsultSettingsRespDTO.setRemark("??????");
        }
        return submitConsultSettingsRespDTO;
    }

    private SubmitConsultSettingsReqDTO convertConsultSettings(SubmitConsultSettingsExcelData demoData) {
        SubmitConsultSettingsReqDTO submitConsultSettingsReqDTO = new SubmitConsultSettingsReqDTO();
        submitConsultSettingsReqDTO.setDoctorId(demoData.getDoctorId());
        submitConsultSettingsReqDTO.setMasterCode("FOSUN_HEALTH");
        submitConsultSettingsReqDTO.setConsultMode(demoData.getConsultMode());
        submitConsultSettingsReqDTO.setSettingsSwitch(demoData.getSettingsSwitch());
        submitConsultSettingsReqDTO.setServicePrice(Long.valueOf(demoData.getServicePrice()));
        submitConsultSettingsReqDTO.setChargebackTime("48");
        submitConsultSettingsReqDTO.setReqSystem("BYX-SYS");
        submitConsultSettingsReqDTO.setTraceId(UUID.randomUUID().toString());
        return submitConsultSettingsReqDTO;
    }

    @Test
    public void simplecsvRead() {
        // ??????1???JDK8+ ,?????????????????????DemoDataListener
        // since: 3.0.0-beta1
//        String fileName = "C:\\Users\\Lance\\Desktop\\????????????????????????.xlsx";
        String fileName = "C:\\Users\\19695\\Desktop\\815\\7W.csv";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet ????????????????????????
        // ?????????????????????100????????? ?????????????????? ??????????????????????????????
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
//                    docIds.add(demoData.getDocId());
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();


        List<List<String>> lists= Lists.partition(docIds,500);
        int n = 1;
        for (List<String> list :lists) {
            ArrayList<DemoData> dataaaaa = new ArrayList<DemoData>();
            //TODO ??????
            for (String docId:list) {
                DemoData demoData = new DemoData();
                try {
                    if (StringUtils.isBlank(docId)) {
                        continue;
                    }
                    Result<Boolean> test = test(docId);
//                    demoData.setDocId(docId);
                    demoData.setCallId(test.getCallId());
                    demoData.setSuccess(test.isSuccess()?"??????":"??????");
                    demoData.setResult(test.getResult()?"??????":"??????");
                    demoData.setErrorCode(test.getErrorCode());
                    demoData.setErrorMsg(test.getErrorMsg());
                    dataaaaa.add(demoData);
                } catch (Exception e) {
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("???????????? ????????????"+e);
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    e.printStackTrace();
//                    demoData.setDocId(docId);
                    demoData.setException(e.toString());
                    dataaaaa.add(demoData);
                }
            }
            //TODO ??????
//            list.parallelStream().forEach(x->{
//                DemoData demoData = new DemoData();
//                try {
//                    Result<Boolean> test = test(x);
//                    demoData.setDocId(x);
//                    demoData.setCallId(test.getCallId());
//                    demoData.setSuccess(test.isSuccess()?"??????":"??????");
//                    demoData.setResult(test.getResult()?"??????":"??????");
//                    demoData.setErrorCode(test.getErrorCode());
//                    demoData.setErrorMsg(test.getErrorMsg());
//                    dataaaaa.add(demoData);
//                } catch (Exception e) {
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("???????????? ????????????"+e);
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    e.printStackTrace();
//                    demoData.setDocId(x);
//                    demoData.setException(e.toString());
//                    dataaaaa.add(demoData);
//                }
//            });
            // ??????1 JDK8+
            String s = UUID.randomUUID().toString();
            // since: 3.0.0-beta1
            String fileNameOut = "C:\\Users\\19695\\Desktop\\815\\result\\??????????????????"+n+"=="+s+".xlsx";
            // ?????? ????????????????????????class??????????????????????????????sheet?????????????????? ??????????????????????????????
            // ?????????????????????03 ??? ??????excelType????????????
            EasyExcel.write(fileNameOut, DemoData.class).sheet("????????????").doWrite(dataaaaa);
            n++;
        }

        try {
            Thread.sleep(99999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ???????????????
     * <p>
     * 1. ??????excel????????????????????? ??????{@link DemoData}
     * <p>
     * 2. ??????????????????????????????excel?????????????????????excel???????????????????????????????????????{@link }
     * <p>
     * 3. ???????????????
     */
    @Test
    public void simplecsvssRead() {
        // ??????1???JDK8+ ,?????????????????????DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "C:\\Users\\Lance\\Desktop\\sqlResult_3406031.csv";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet ????????????????????????
        // ?????????????????????100????????? ?????????????????? ??????????????????????????????

        // ?????? ????????????????????????class??????????????????????????????sheet
        EasyExcel.read(fileName, DemoData.class, new DemoExceptionListener()).sheet().doRead();

        // ??????1 JDK8+
        // since: 3.0.0-beta1
        String fileNameOut = "C:\\Users\\Lance\\Desktop\\sqlResp.xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet?????????????????? ??????????????????????????????
        // ?????????????????????03 ??? ??????excelType????????????
        EasyExcel.write(fileNameOut, DemoData.class).sheet("????????????").doWrite(data());


    }

    @Test
    public void getAllCities() throws NoSuchMethodException {
        List<City> mockCities = createCities();
        String uri = "/city/allCities";
        wireMockRule.stubFor(get(urlEqualTo(uri)).willReturn(aResponse().withBody(JSON.toJSONString(mockCities))));
        List<City> cityList = cityService.getAllCities();
        assertTrue(mockCities.containsAll(cityList));
        assertTrue(cityList.containsAll(mockCities));
    }

    @Test
    public void getCity() {
        int id = 1;
        String uri = "/city/getById?id=" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri)).willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City city = HttpApiProxyFactory.newProxy(CityService.class).getCity(id);
        assertEquals(mockCity, city);
    }

    @Test
    public void saveCities() {
        List<City> mockCities = createCities();
        String uri = "/city/save";
        wireMockRule.stubFor(post(urlEqualTo(uri)).withRequestBody(equalToJson(JSON.toJSONString(mockCities))).willReturn(aResponse().withBody("true")));
        boolean rs = cityService.saveCities(mockCities);
        assertTrue(rs);
    }

    @Test
    public void saveCity() {
        String uri = "/city/saveCity";
        Map<String, Object> body = new HashMap<String, Object>();
        int id = 1;
        String name = "??????";
        body.put("name", name);
        body.put("id", id);
        wireMockRule.stubFor(post(urlEqualTo(uri))
                .withRequestBody(equalToJson(JSON.toJSONString(body)))
                .willReturn(aResponse().withBody("true")));
        boolean rs = cityService.saveCity(id, name);
        assertTrue(rs);
    }

    @Test
    public void getCityByName() throws UnsupportedEncodingException {
        String cityName = "??????";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8");
        City city = createCity(cityName);
        ResultBean<City> mockCityResult = new ResultBean<City>(0, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        ResultBean<City> cityResultBean = cityService.getCityByName(cityName);
        assertEquals(mockCityResult, cityResultBean);

        // ???????????????????????? resultBean ????????????????????? ResultBeanResponseProcessor ?????????????????????
        cityResultBean = cityServiceWithResultBeanResponseProcessor.getCityByName(cityName);
        assertEquals("???????????? resultBean ??????ResultBeanResponseProcessor ?????????????????????", mockCityResult, cityResultBean);
    }

    @Test
    public void testWithConfigPathVariable() throws UnsupportedEncodingException {
        String cityName = "??????";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8") + "&id=";
        City city = createCity(cityName);
        ResultBean<City> mockCityResult = new ResultBean<City>(0, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        ResultBean<City> cityResultBean = cityService.getCityByNameWithConfigVariable();
        assertEquals(mockCityResult, cityResultBean);

        // ???????????????????????? resultBean ????????????????????? ResultBeanResponseProcessor ?????????????????????
        cityResultBean = cityServiceWithResultBeanResponseProcessor.getCityByNameWithConfigVariable();
        assertEquals("???????????? resultBean ??????ResultBeanResponseProcessor ?????????????????????", mockCityResult, cityResultBean);
    }

    @Test
    public void getCityRest() {
        int id = 1;
        String uri = "/city/getCityRest/" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri)).willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City result = cityService.getCityRest(id);
        assertEquals(mockCity, result);
    }

    @Test
    public void getCityRestWithDefaultPathVal() {
        int id = 1;
        String uri = "/city/getCityRest/" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri)).willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City result = cityService.getCityRestWithDefaultPathVal(null);
        assertEquals(mockCity, result);
    }

    @Test
    public void updateCity() {
        int id = 1;
        String name = "??????";
        String uri = "/city/" + id;
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("id", id);
        body.put("name", name);
        wireMockRule.stubFor(put(urlEqualTo(uri))
                .withRequestBody(equalToJson(JSON.toJSONString(body)))
                .willReturn(aResponse().withBody("true")));
        boolean result = cityService.updateCity(id, name);
        assertTrue(result);
    }

    @Test
    public void patchCity() {
        int id = 1;
        String name = "??????";
        String uri = "/city/patchCity";
        City city = new City(id, name);
        if (requestor instanceof JsoupRequestor) {
            // Jsoup does not support real PATCH method, but it can using X-HTTP-Method-Override header to send a fake PATCH
            wireMockRule.stubFor(post(urlEqualTo(uri))
                    .withHeader("X-HTTP-Method-Override", equalTo("PATCH"))
                    .willReturn(aResponse().withBody("true")));
        } else {
            wireMockRule.stubFor(patch(urlEqualTo(uri))
                    .withRequestBody(equalToJson(JSON.toJSONString(city)))
                    .willReturn(aResponse().withBody("true")));
        }
        boolean result = cityService.patchCity(city);
        assertTrue(result);
    }

    @Test
    public void testHead() {
        String uri = "/city/head";
        wireMockRule.stubFor(WireMock.head(urlEqualTo(uri)).willReturn(ok()));
        cityService.head();
    }

    @Test
    public void testTrace() {
        String uri = "/city/trace";
        wireMockRule.stubFor(trace(urlEqualTo(uri)).willReturn(aResponse().withBody("true")));
        boolean result = cityService.trace();
        assertTrue(result);
    }

    @Test
    public void testOptions() {
        String uri = "/city/options";
        wireMockRule.stubFor(WireMock.options(urlEqualTo(uri)).willReturn(aResponse().withBody("true")));
        boolean result = cityService.options();
        assertTrue(result);
    }

    @Test
    public void getCityWithErrHeaders() {
        try {
            int id = 1;
            cityService.getCityWithErrHeaders(id, "");
            fail("getCityWithErrHeaders should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("getCityWithErrHeaders");
        }
    }

    @Test
    public void getCityWithHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        String key = "auth";
        String key2 = "auth2";
        headers.put(key, "123");
        headers.put(key2, "321");
        int id = 1;
        String uri = "/city/getCityRest/" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .withHeader(key, equalTo(headers.get(key)))
                .withHeader(key2, equalTo(headers.get(key2)))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City result = cityService.getCityWithHeaders(id, headers);
        assertEquals(mockCity, result);
    }

    @Test
    public void getCityWithCookies() {
        Map<String, String> cookies = new HashMap<String, String>();
        String key = "auth";
        String key2 = "auth2";
        cookies.put(key, "123");
        cookies.put(key2, "321");
        int id = 1;
        String uri = "/city/getCityRest/" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .withCookie(key, equalTo(cookies.get(key)))
                .withCookie(key2, equalTo(cookies.get(key2)))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City result = cityService.getCityWithCookies(id, cookies);
        assertEquals(mockCity, result);
    }

    @Test
    public void listCity() {
        List<City> mockCities = createCities();
        String uri = "/city/listCity";
        String headerKey = "header1";
        String headerValue = "value11";
        String cookie1 = "c1=c; path=\"/\", c2=cc; domain=\"localhost\"";
        String cookie2 = "c3=ccc; path=\"/\", c2=cc; domain=\"localhost\"";
        wireMockRule.stubFor(get(urlEqualTo(uri))
                // ?????? header ?????????
                .willReturn(okJson(JSON.toJSONString(mockCities))
                        .withHeader(headerKey, headerValue)
                        .withHeader("Set-Cookie", cookie1)
                        .withHeader("Set-Cookie", cookie2)));
        HttpResponse response = cityService.listCity();
        System.out.println("?????????headers:" + response.getHeaders());
        assertEquals(headerValue, response.getHeader(headerKey));
        assertEquals("application/json", response.getHeader("Content-Type"));
        assertEquals("c", response.getCookie("c1"));
        assertEquals("ccc", response.getCookie("c3"));

        Map<String, List<String>> headers = response.multiHeaders();
        System.out.println(headers);
        assertEquals(headers.get("Content-Type"), Collections.singletonList("application/json"));
        assertEquals(headers.get("Set-Cookie"), Arrays.asList(cookie1, cookie2));
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            List<String> setCookieHeaders = response.getHeaders(entry.getKey());
            System.out.println(setCookieHeaders);
            assertEquals(setCookieHeaders, entry.getValue());
        }
        List<City> cityList = JSON.parseArray(response.getBody(), City.class);
        assertTrue(mockCities.containsAll(cityList));
        assertTrue(cityList.containsAll(mockCities));
    }

    @Test
    public void hasCity() {
        int id = 1;
        City mockCity = createCity(id);
        String uri = "/city/getCity";
        wireMockRule.stubFor(get(urlPathEqualTo(uri))
                .withQueryParam("id", equalTo(String.valueOf(mockCity.getId())))
                .withQueryParam("name", equalTo(mockCity.getName()))
                .willReturn(aResponse().withBody("true")));
        boolean exists = cityService.hasCity(mockCity);
        assertTrue(exists);
    }

    /**
     * ??????????????????
     */
    @Test
    public void saveCityForm() throws UnsupportedEncodingException {
        City city = CityUtil.createCity(1);
        String uri = "/city/saveCity";
        wireMockRule.stubFor(post(urlPathEqualTo(uri))
                .withRequestBody(equalTo("name=" + URLEncoder.encode(city.getName(), "utf-8") + "&id=" + city.getId()))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded"))
                .willReturn(aResponse().withBody("true")));
        boolean rs = cityService.saveCityForm(city);
        assertTrue(rs);
    }

    @Test
    public void getCities() {
        String uri = "/city/getByIds";
        List<Integer> cityIds = Arrays.asList(1, 2, 3);
        List<City> rs = CityUtil.getCities(cityIds);
        wireMockRule.stubFor(get(urlPathEqualTo(uri))
                .withQueryParam("id", equalTo("1"))
                .withQueryParam("id", equalTo("2"))
                .withQueryParam("id", equalTo("3"))
                .willReturn(aResponse().withBody(JSON.toJSONString(rs))));
        List<City> cities = cityService.getCities(cityIds);
        assertEquals(rs, cities);
        System.out.println(cities);
    }

    @Test
    public void deleteCity() {
        for (int i = 0; i < 10; i++) {
            int id = 1;
            String uri = "/city/" + id;
            wireMockRule.stubFor(delete(urlPathEqualTo(uri))
                    .willReturn(ok().withBody(JSON.toJSONString(new ResultBean<String>(0, "OK")))));
            // ???????????????????????????????????????????????????
            cityService.deleteCity(id);
            cityServiceWithResultBeanResponseProcessor.deleteCity(id);
        }
    }

    @Test
    public void uploadTest() throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try {
                        upload();
                        multipartTest();
                    } catch (Exception e) {
                        fail(e.getMessage());
                    }
                    return null;
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.MINUTES);
    }

    @Test
    public void upload() throws IOException {
        String uri = "/city/picture/upload";
        String randomName = UUID.randomUUID().toString();
        String fileName = "conf.properties";
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(fileName);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            wireMockRule.stubFor(post(urlPathEqualTo(uri))
                    .withMultipartRequestBody(aMultipart("media").withBody(binaryEqualTo(bytes)))
                    .withMultipartRequestBody(aMultipart("fileName").withBody(equalTo(fileName)))
                    .willReturn(aResponse().withBody(randomName)));
            String name = cityService.upload(fileName, new ByteArrayInputStream(bytes));
            assertEquals(randomName, name);
        } catch (IOException e) {
            e.printStackTrace();
            fail("read test file error");
        } finally {
            closeStream(in);
        }
    }

    @Test
    public void multipartTest() throws IOException, InterruptedException {
        String uri = "/city/files/upload";
        String randomName = UUID.randomUUID().toString();
        String fileName1 = "conf.properties";
        String fileName2 = "conf2.properties";
        InputStream in1 = null;
        InputStream in2 = null;
        try {
            in1 = getClass().getClassLoader().getResourceAsStream(fileName1);
            in2 = getClass().getClassLoader().getResourceAsStream(fileName2);
            byte[] bytes1 = new byte[in1.available()];
            in1.read(bytes1);

            byte[] bytes2 = new byte[in2.available()];
            in2.read(bytes2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append(UUID.randomUUID());
            }
            in1.read(sb.toString().getBytes());
            wireMockRule.stubFor(post(urlPathEqualTo(uri))
                    .withMultipartRequestBody(aMultipart("conf1").withBody(binaryEqualTo(bytes1)))
                    .withMultipartRequestBody(aMultipart("conf2").withBody(binaryEqualTo(bytes2)))
                    .willReturn(aResponse().withBody(randomName)));

            MultiPart.Part part1 = new MultiPart.Part("conf1", fileName1, new ByteArrayInputStream(bytes1));
            MultiPart.Part part2 = new MultiPart.Part("conf2", fileName2, new ByteArrayInputStream(bytes2));

            MultiPart multiPart = new MultiPart();
            multiPart.addPart(part1);
            multiPart.addPart(part2);

            String name = cityService.multiPartForm(multiPart);
            assertEquals(randomName, name);
        } catch (IOException e) {
            e.printStackTrace();
            fail("submit multipart form error");
        } finally {
            closeStream(in1);
            closeStream(in2);
        }
    }

    @Test
    public void preprocessorTest() {
        HttpApiProxyFactory factory = new HttpApiProxyFactory(new RequestPreprocessor() {
            @Override
            public void process(HttpRequest request) {
                request.addCookie("authCookies", authKey);
                request.addHeader("authHeaders", authKey);
                Method method = CURRENT_METHOD_THREAD_LOCAL.get();
                System.out.println("current method " + method.getName());
                try {
                    assertEquals(CityService.class.getMethod("getCity", int.class), method);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    fail("");
                }
            }
        });
        CityService cityServiceWithPreprocessor = factory.getProxy(CityService.class);
        int id = 1;
        String uri = "/city/getById?id=" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .withCookie("authCookies", equalTo(authKey))
                .withHeader("authHeaders", equalTo(authKey))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCity))));
        City city = cityServiceWithPreprocessor.getCity(id);
        assertNull("????????????ThreadLocal???????????????", RequestPreprocessor.CURRENT_METHOD_THREAD_LOCAL.get());
        assertEquals(mockCity, city);
    }

    @Test
    public void responseProcessTest() {
        ResponseProcessor cityResultProcessor = new ResponseProcessor() {
            @Override
            public Object process(HttpResponse response, Method method) {
                ResultBean<City> cityResultBean = JSON.parseObject(response.getBody(), new TypeReference<ResultBean<City>>() {
                });
                return cityResultBean.getData();
            }
        };
        HttpApiProxyFactory factory = new HttpApiProxyFactory(cityResultProcessor);
        CityService cityServiceWithResponseProcessor = factory.getProxy(CityService.class);
        int id = 1;
        String uri = "/city/getById?id=" + id;
        City mockCity = createCity(id);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(new ResultBean<City>(0, mockCity)))));
        City city = cityServiceWithResponseProcessor.getCity(id);
        assertEquals(mockCity, city);
    }

    @Test
    public void getAllCitiesWithResultBeanResponseProcessor() throws NoSuchMethodException {
        List<City> mockCities = createCities();
        String uri = "/city/allCities";
        wireMockRule.stubFor(get(urlEqualTo(uri)).willReturn(aResponse().withBody(JSON.toJSONString(new ResultBean<List<City>>(0, mockCities)))));
        List<City> cityList = cityServiceWithResultBeanResponseProcessor.getAllCities();
        assertTrue(mockCities.containsAll(cityList));
        assertTrue(cityList.containsAll(mockCities));
    }

    @Test
    public void getCityWithResultBean() throws UnsupportedEncodingException {
        String cityName = "??????";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8");
        City city = createCity(cityName);
        ResultBean<City> mockCityResult = new ResultBean<City>(1, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        City result = cityServiceWithResultBeanResponseProcessor.getCityWithResultBean(cityName);
        assertEquals(city, result);
    }

    @Test
    public void getCityWithStatusCode() throws UnsupportedEncodingException {
        String cityName = "??????";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8");
        City city = createCity(cityName);
        ResultBeanWithStatusAsCode<City> mockCityResult = new ResultBeanWithStatusAsCode<City>(1, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        City result = cityServiceWithResultBeanResponseProcessor.getCityWithStatusCode(cityName);
        assertEquals(city, result);
    }

    @Test
    public void getCityObject() throws UnsupportedEncodingException {
        String uri = "/city/getCityObject";
        String cityString = JSON.toJSONString(new ResultBean<String>(0, "123"));
        wireMockRule.stubFor(get(urlEqualTo(uri))
                // ?????????????????????cookie
                .withHeader("globalHeader1", equalTo("ok"))
                .withHeader("globalHeader2", equalTo("yes"))
                .withHeader("h3", equalTo("haha"))
                .withCookie("globalCookie", equalTo("ok"))
                // ????????? UserAgent ??????
                .withHeader("User-Agent", equalTo("JUnit"))
                .willReturn(aResponse().withBody(cityString)));
        Object obj = cityService.getCityObject();
        assertEquals(obj, cityString);
        obj = cityServiceWithResultBeanResponseProcessor.getCityObject();
        assertEquals("123", obj);
    }

    @Test
    public void getCityName() {
        String uri = "/city/getCityName";
        String cityString = JSON.toJSONString(new ResultBean<String>(0, "??????"));
        wireMockRule.stubFor(get(urlPathEqualTo(uri))
                .withQueryParam("id", equalTo("1"))
                // ?????????????????????cookie?????????key??????????????????????????????
                .withHeader("happy", equalTo("done"))
                .withHeader("h3", equalTo("nice"))
                .withHeader("globalHeader1", equalTo("ok"))
                .withHeader("globalHeader2", equalTo("yes"))
                .withCookie("globalCookie", equalTo("bad"))
                .withCookie("auth", equalTo("ok"))
                // ????????? UserAgent ???????????????????????????????????????
                .withHeader("User-Agent", equalTo("cityAgent"))
                .withHeader("Content-Type", equalTo("text/plain"))
                .willReturn(aResponse().withBody(cityString)));
        Object obj = cityService.getCityName(1);
        assertEquals(obj, cityString);
        obj = cityServiceWithResultBeanResponseProcessor.getCityName(1);
        assertEquals("??????", obj);
    }

    @Test
    public void getCityByComplicatedInfo() throws UnsupportedEncodingException {
        String uri = "/city/getCityByComplicatedInfo";
        List<City> cities = CityUtil.createCities();
        City city = CityUtil.createCity(1);
        ComplicatedInfo info = new ComplicatedInfo(cities, "OK", city);
        Map<String, String> map = ParamUtils.toMapStringString(info, "");
        StringBuilder expectedRequestBody = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            expectedRequestBody.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        wireMockRule.stubFor(post(urlPathEqualTo(uri))
                .withRequestBody(equalTo(expectedRequestBody.substring(0, expectedRequestBody.length() - 1)))
                .willReturn(aResponse().withBody(JSON.toJSONString(city))));
        City rs = cityService.getCityByComplicatedInfo(info);
        assertEquals(city, rs);
    }

    @Test
    public void getDate() throws UnsupportedEncodingException {
        String uri = "/city/date";
        Map<String, Object> param = new HashMap<String, Object>();
        Date now = new Date();
        param.put("date", now);
        wireMockRule.stubFor(post(urlPathEqualTo(uri))
                .withRequestBody(equalToJson(JSON.toJSONString(param)))
                .willReturn(aResponse().withBody(JSON.toJSONString(now))));
        Date date = cityService.getDate(now);
        assertEquals(date.getTime(), now.getTime());

        ResultBean<Date> resultBean = new ResultBean<Date>(0, now);
        wireMockRule.stubFor(post(urlPathEqualTo(uri))
                .withRequestBody(equalToJson(JSON.toJSONStringWithDateFormat(param, "")))
                .willReturn(aResponse().withBody(JSON.toJSONString(resultBean))));
        date = cityServiceWithResultBeanResponseProcessor.getDate(now);
        assertEquals(date.getTime(), now.getTime());
    }

    @Test
    public void getString() {
        String uri = "/city/string";
        String rs = "OK";
        wireMockRule.stubFor(get(urlPathEqualTo(uri)).willReturn(aResponse().withBody(rs)));
        String str = cityService.getString();
        assertEquals(rs, str);
        str = cityServiceWithResultBeanResponseProcessor.getString();
        assertEquals(rs, str);
    }

    @Test
    public void test1(){
        String fileName = "C:\\Users\\Lance\\Downloads\\??????ID.xlsx";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // ?????? ????????????????????????class??????????????????????????????sheet ????????????????????????
        // ?????????????????????100????????? ?????????????????? ??????????????????????????????
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getCallId());
                } catch (Exception e) {
                    System.out.println("??????????????????");
                }
            }
        })).sheet().doRead();
        System.out.println(docIds);
        String sql = "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????/????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '?????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '??????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '?????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '?????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????????????????????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '?????????????????????????????????????????????????????????????????????', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '???????????????????????????', 0, 1, NULL, 'D', 0);\n";

        ArrayList<String> sqls = new ArrayList<>();
        for (String doctorId:docIds) {
            String docid = sql.replaceAll("DOCID", doctorId);
            sqls.add(docid);
        }

        FileUtils.FileWriteList("C:\\Users\\Lance\\Downloads\\sql2.text",sqls);
        System.out.println("");

    }


}