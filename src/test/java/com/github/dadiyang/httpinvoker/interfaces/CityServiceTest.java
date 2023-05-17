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
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import wiremock.com.google.common.collect.Lists;
import wiremock.com.google.common.collect.Maps;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public void 退钱(){
        Set<String> diagnoseIds = getDocIds();

        AtomicInteger i = new AtomicInteger();
        diagnoseIds.parallelStream().forEach(diagnoseId -> {
            i.getAndIncrement();
            System.err.println("================="+i+"================");
            try {
                refundAndRepealWipedAssets(diagnoseId);
//                CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
//                closeConsultOrder.setDiagnoseId(diagnoseId);
//                System.out.println("关单参数"+JSON.toJSONString(closeConsultOrder));
//                Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
//                System.out.println("关单返回"+JSON.toJSONString(result));

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
    public void 补发帮指数(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("202301292139013500");
        strings.add("202301212221416709");
        strings.add("202301201803360707");
//        boolean contains = CollectionUtils.contains(strings.listIterator(), "202301032220434474");
//        System.out.println(contains);

        for (String diagnoseId:strings) {
            BzsReqDTO bzsReqDTO = new BzsReqDTO();
            bzsReqDTO.setReqSystem("补发");
            bzsReqDTO.setDiagnoseId(diagnoseId);
            bzsReqDTO.setTraceId(UUID.randomUUID().toString());
            HashMap<String, String> headers = new HashMap<>();
//            headers.put("X-FOSUN-TOKEN", "bDRLTEtha0lUUkdLVHJFMXczU3FHNE9Qa29NN0VqS2ZoSGFha0g2MHhFbVNqQ2VrTnlYTzFLVmRhSUpCKzN6OEdsaTd1UGwxaGEyYXlWQjBYMXpRS1dxdC8xT0VMWC9hWWVrcWNxamRUNCszUnZob3N3M2JNVi9LK0Q0M3F0L3EvMitwTFFBWForWTFQTGRWSzNJWFpmSlFhc292VzBXSVlKWUJ3RDY5cHAxdDhTVExLdnBXVzJNR3R2Vk5HcGdnczJ0WkxFMkpsYkJ3RWxjNDdEdVY0TFpNRU03TmVldGVGTERudlpHODFqWT0");
            headers.put("X-FOSUN-TOKEN", "bDRLTEtha0lUUkdLVHJFMXczU3FHNE9Qa29NN0VqS2ZoSGFha0g2MHhFbVNqQ2VrTnlYTzFLVmRhSUpCKzN6OEdsaTd1UGwxaGEyYXlWQjBYMXpRS1dxdC8xT0VMWC9hWWVrcWNxamRUNCszUnZob3N3M2JNVi9LK0Q0M3F0L3EvMitwTFFBWForWTFQTGRWSzNJWFpUd3BIc0FlY0dVS0xvUi9uVkVRcTdDTndYWnNpRlU4UnF5Z0oxZWtJZFRBczJ0WkxFMkpsYkJ3RWxjNDdEdVY0TFpNRU03TmVldGVGTERudlpHODFqWT0");
            Result<Boolean> result = cityService.bzsPush(bzsReqDTO,headers);
            System.out.println(result);
        }
        try {
            Thread.sleep(9999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 更新线上数据() throws Exception {
        ArrayList<CleanDoctorData> docData = getDocData();
        Map<String, List<CleanDoctorData>> excelDate = docData.stream().filter(x -> (!StringUtils.isBlank(x.getDocId()))).collect(Collectors.groupingBy(CleanDoctorData::getDocId));
        System.out.println(excelDate);
        ArrayList<CleanDoctorData> remarkList = new ArrayList<>();
        excelDate.keySet().parallelStream().forEach( doctorId ->{
            try {
                updateDoctorInfo(excelDate, doctorId);
            }catch (Exception e){
                List<CleanDoctorData> cleanDoctorData = excelDate.get(doctorId);
                if (!CollectionUtils.isEmpty(cleanDoctorData)){
                    CleanDoctorData cleanDoctorRemark = cleanDoctorData.get(0);
                    if (cleanDoctorRemark != null) {
                        cleanDoctorRemark.setRemark(e.getMessage());
                        remarkList.add(cleanDoctorRemark);
                    }
                }
            }
        }  );
        System.out.println(remarkList);
//        for (String doctorId:excelDate.keySet()) {
//            try {
//                updateDoctorInfo(excelDate, doctorId);
//            }catch (Exception e){
//                List<CleanDoctorData> cleanDoctorData = excelDate.get(doctorId);
//                if (CollectionUtils.isEmpty(cleanDoctorData)){
//                    CleanDoctorData cleanDoctorRemark = cleanDoctorData.get(0);
//                    if (cleanDoctorRemark != null) {
//                        cleanDoctorRemark.setRemark(e.getMessage());
//                        remarkList.add(cleanDoctorRemark);
//                    }
//                }
//            }
//        }
    }

    private void updateDoctorInfo(Map<String, List<CleanDoctorData>> excelDate, String doctorId) throws Exception {
        List<CleanDoctorData> cleanDoctorData = excelDate.get(doctorId);
        QueryMultiConsultSettingsReqDTO queryMultiConsultSettingsReqDTO = new QueryMultiConsultSettingsReqDTO();
        queryMultiConsultSettingsReqDTO.setDoctorId(doctorId);
        queryMultiConsultSettingsReqDTO.setReqSystem("API-清洗医生数据");
        String traceId = UUID.randomUUID().toString();
        queryMultiConsultSettingsReqDTO.setTraceId(traceId);
        Result<List<QueryConsultSettingsResDTO>> listResult = cityService.queryConsultSettingsList(queryMultiConsultSettingsReqDTO);
        if (listResult== null || !listResult.isSuccess() ||  listResult.getResult() == null) {
            log.error("查询失败:{}----{}",queryMultiConsultSettingsReqDTO,listResult);
            throw new Exception("查询失败:" + doctorId);
        }
        if (true){
            throw new Exception("查询失败:" + doctorId);
        }
        if (true){
            return;
        }
        System.err.println("=====================");
        System.err.println("=====================");
        System.err.println("=====================");
        System.err.println("=====================");
        System.err.println("=====================");
        System.err.println("=====================");
        List<QueryConsultSettingsResDTO> result = listResult.getResult();
        //医生不可用
        Result<Boolean> delDocSetting = cityService.delDocSetting(queryMultiConsultSettingsReqDTO);
        if (delDocSetting== null || !delDocSetting.isSuccess() ||  !delDocSetting.getResult()) {
            log.error("删除医生信息失败:{}----{}",queryMultiConsultSettingsReqDTO,listResult);
            throw new Exception("删除医生信息失败:"+ doctorId);
        }
        for (QueryConsultSettingsResDTO resDTO : result) {
            //修改医生数据
            SubmitConsultSettingsReqDTO reqDTO = ConsultSettingsConvert.getSubmitConsultSettingsReqDTO(traceId, resDTO,cleanDoctorData);
            log.info("修改医生信息接口:{}",JSON.toJSONString(reqDTO));
            Result<Boolean> submitConsultSettings = cityService.submitConsultSettings(reqDTO);
            log.info("修改医生信息接口返回:{}",submitConsultSettings);
            if (submitConsultSettings== null || !submitConsultSettings.isSuccess() ||  !submitConsultSettings.getResult()) {
                log.error("修改医生信息失败:{}----{}",queryMultiConsultSettingsReqDTO,listResult);
                throw new Exception("修改医生信息失败:"+ doctorId);
            }
        }
    }

    /**
     *功能描述 : 清洗医生数据 70%
     * @author boyuxin
     * @date 2022/11/28 11:30
     * @return void
     */
    @Test
    public void clearDoctor(){

        Set<String> docIds = getDocIds();


//        Set<String> docIds = new HashSet<>();
        docIds.add("186982");
        //查询医生数据
        int i = 0;
        for (String docId:docIds) {
            i++;
            System.err.println("================="+i+"================");
            QueryMultiConsultSettingsReqDTO queryMultiConsultSettingsReqDTO = new QueryMultiConsultSettingsReqDTO();
            queryMultiConsultSettingsReqDTO.setDoctorId(docId);
            queryMultiConsultSettingsReqDTO.setReqSystem("API-清洗医生数据");
            String traceId = UUID.randomUUID().toString();
            queryMultiConsultSettingsReqDTO.setTraceId(traceId);
            Result<List<QueryConsultSettingsResDTO>> listResult = cityService.queryConsultSettingsList(queryMultiConsultSettingsReqDTO);
            if (listResult== null || !listResult.isSuccess() ||  listResult.getResult() == null) {
                log.error("查询失败:{}----{}",queryMultiConsultSettingsReqDTO,listResult);
                continue;
            }
            //医生不可用
            Result<Boolean> delDocSetting = cityService.delDocSetting(queryMultiConsultSettingsReqDTO);
            log.info("删除医生信息返回:{}",delDocSetting);
            List<QueryConsultSettingsResDTO> result = listResult.getResult();
            for (QueryConsultSettingsResDTO resDTO : result) {
                String consultMode = resDTO.getConsultMode();
//                if ("TELEPHONE".equals(consultMode)) {
//                    log.info("电话问诊");
//                    continue;
//                }
                //修改医生数据
                SubmitConsultSettingsReqDTO reqDTO = ConsultSettingsConvert.getSubmitConsultSettingsReqDTO(traceId, resDTO , null);
                log.info("修改医生信息接口:{}",JSON.toJSONString(reqDTO));
                Result<Boolean> submitConsultSettings = cityService.submitConsultSettings(reqDTO);
                log.info("修改医生信息接口返回:{}",submitConsultSettings);
            }
        }
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    @Test
    public void 验签(){
        //系统当前时间
        String timestamp = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        //访问路径
        String path = "/api/consult-product/consultSettings/queryConsultSettingsList";
        //加密 AppKey
        String appKey = "D7D9C6393E204EFD96D6AAE8D4EC4C9A";
        //加密 秘钥
        String appSecret = "2A626303AA64444E911E999DDF39B9FA";

        Map<String, String> map = Maps.newHashMapWithExpectedSize(3);
        map.put("timestamp",timestamp);  //值应该为毫秒数的字符串形式
        map.put("path", path);
        map.put("version", "1.0.0");

        //计算签名
        List<String> storedKeys = Arrays.stream(map.keySet().toArray(new String[]{})).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        final String sign = storedKeys.stream().map(key -> String.join("", key, map.get(key))).collect(Collectors.joining()).trim().concat(appSecret);
        //签名转大写
        String signUppercase = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();


        HashMap<String, String> headers = new HashMap<>();
        headers.put("timestamp", timestamp);
        headers.put("appKey", appKey);
        headers.put("sign", signUppercase);
        QueryMultiConsultSettingsReqDTO queryMultiConsultSettingsReqDTO = new QueryMultiConsultSettingsReqDTO();
        queryMultiConsultSettingsReqDTO.setDoctorId("45398");
        Result<List<QueryConsultSettingsResDTO>> listResult = cityService.queryConsultSettingsListProduct(queryMultiConsultSettingsReqDTO,headers);
        System.out.println(listResult);

    }

    @Test
    public void 关闭咨询(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("IMAGE_TEXT");
        strings.add("TELEPHONE");
        strings.add("VIDEO");
        strings.add("ELECTRONIC_PRESCRIPTION");

        ArrayList<String> doctorids = new ArrayList<>();
        doctorids.add("151061");
        doctorids.add("145291");
        doctorids.add("87754");
        doctorids.add("58378");
        doctorids.add("57384");
        doctorids.add("55383");
        doctorids.add("59962");
        doctorids.add("243694");
        doctorids.add("101671");
        doctorids.add("146621");
        doctorids.add("115137");
        doctorids.add("180036");
        doctorids.add("181158");
        doctorids.add("106906");
        doctorids.add("98800");
        doctorids.add("64796");
        doctorids.add("87487");
        doctorids.add("62457");
        doctorids.add("14090");
        doctorids.add("171628");
        doctorids.add("237583");
        for (String dd :doctorids) {
            for (String sss:strings) {
                SubmitConsultSettingsReqDTO reqDTO = new SubmitConsultSettingsReqDTO();
                reqDTO.setDoctorId(dd);
                reqDTO.setMasterCode("FOSUN_HEALTH");
                reqDTO.setSettingsSwitch("OFF");
                reqDTO.setServicePrice(0L);
                reqDTO.setReqSystem("API");
                reqDTO.setTraceId(UUID.randomUUID().toString());
                reqDTO.setConsultMode(sss);
                log.info("修改医生信息接口:{}",JSON.toJSONString(reqDTO));
                Result<Boolean> submitConsultSettings = cityService.submitConsultSettings(reqDTO);
                log.info("修改医生信息接口返回:{}",submitConsultSettings);

            }
        }

        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    private Set<String> getDocIds() {
        String fileName = "/Users/apple/Documents/肿瘤&风免需配置分佣医生明细--数据截止4月25日.xlsx";
        Set<String> docIds = new HashSet<String>();
        EasyExcel.read(fileName, CleanDoctorDemoData.class, new PageReadListener<CleanDoctorDemoData>(dataList -> {
            for (CleanDoctorDemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDocId());
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();
        return docIds;
    }

    private ArrayList<CleanDoctorData>  getDocData() {
        String fileName = "/Users/apple/Desktop/0516.xlsx";
        ArrayList<CleanDoctorData> cleanDoctorData = new ArrayList<>();

        EasyExcel.read(fileName, CleanDoctorData.class, new PageReadListener<CleanDoctorData>(dataList -> {
            for (CleanDoctorData demoData : dataList) {
                try {
                    cleanDoctorData.add(demoData);
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();
        return cleanDoctorData;
    }

    @Test
    public void syncDoctorPatientRelationBillSupport() {
        String fileName = "C:\\Users\\Lance\\Desktop\\无标题2.xlsx";
        ArrayList<ConsultDoctorPatientReqDTO> datas = new ArrayList<>();
        EasyExcel.read(fileName, ConsultDoctorPatientReqDTO.class, new PageReadListener<ConsultDoctorPatientReqDTO>(dataList -> {
            for (ConsultDoctorPatientReqDTO demoData : dataList) {
                try {
                    datas.add(demoData);
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();

        for (ConsultDoctorPatientReqDTO da:datas){
            da.setBusinessType("FAMILY_DOCTOR_CONSULT");
            da.setEntranceSource("FOSUN_HEALTH");
            da.setSourceType(2);
            da.setRealDoctorId(da.getDoctorId());
            da.setPatientAge(null);
            log.info("入参:{}",da);
            System.out.println(JSON.toJSONString(da));
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM3MDc5MDc4OTUsInBheWxvYWQiOiJ7XCJpZFwiOjksXCJ1c2VybmFtZVwiOlwi5rWL6K-VLeWImOS8iuWHoVwiLFwibG9naW5OYW1lXCI6XCIxMzU4NTUwNDM4M1wiLFwidXNlclR5cGVcIjoxLFwiZG9jdG9ySWRcIjoxODY5ODIsXCJyb29tSWRcIjoxODY5ODIsXCJ0b2tlblwiOlwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SmxlSEFpT2pFMk56TTNNRGM1TURjNE9UUXNJbkJoZVd4dllXUWlPaUo3WENKcFpGd2lPakU0TmprNE1uMGlmUS5DaVVjSEYwdDJtcE5qdHZaNTBvLXdsb19nbWhZMWkzaXRvbFBqOGg4WE5vXCJ9In0.MFEODwPwugRfqyV4oC1HqWCPPQQrq6P75x3iMK1IrC8");

            Result<Boolean> result = cityService.syncDoctorPatientRelationBillSupport(da,headers);
            log.info("出参:{}",result);


        }


        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     *功能描述 : 同步医患关系
     * @author boyuxin
     * @date 2023/2/21 9:40
     * @return void
     */
    @Test
    public void syncDoctorPatientRelationBillSupportOne() {
        ConsultDoctorPatientReqDTO da = new ConsultDoctorPatientReqDTO();
        da.setDoctorId("61900");
        da.setOwnerId("9972554");
        da.setPatientName("颜万朗");
        da.setPatientId("13450348");
        da.setPatientTel("15007716396");
        da.setBusinessType("FAMILY_DOCTOR_CONSULT");
        da.setEntranceSource("FOSUN_HEALTH");
        da.setSourceType(2);
        da.setRealDoctorId(da.getDoctorId());
        da.setPatientAge(null);
        log.info("入参:{}",da);
        System.out.println(JSON.toJSONString(da));
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM3MDc5MDc4OTUsInBheWxvYWQiOiJ7XCJpZFwiOjksXCJ1c2VybmFtZVwiOlwi5rWL6K-VLeWImOS8iuWHoVwiLFwibG9naW5OYW1lXCI6XCIxMzU4NTUwNDM4M1wiLFwidXNlclR5cGVcIjoxLFwiZG9jdG9ySWRcIjoxODY5ODIsXCJyb29tSWRcIjoxODY5ODIsXCJ0b2tlblwiOlwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SmxlSEFpT2pFMk56TTNNRGM1TURjNE9UUXNJbkJoZVd4dllXUWlPaUo3WENKcFpGd2lPakU0TmprNE1uMGlmUS5DaVVjSEYwdDJtcE5qdHZaNTBvLXdsb19nbWhZMWkzaXRvbFBqOGg4WE5vXCJ9In0.MFEODwPwugRfqyV4oC1HqWCPPQQrq6P75x3iMK1IrC8");

        Result<Boolean> result = cityService.syncDoctorPatientRelationBillSupport(da,headers);
        log.info("出参:{}",result);
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        log.info("调用返回=={}",test);
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
        System.out.println("导数据-请求医生ID"+docId);
        Result<Boolean> result = cityService.getAllCities11(mergeConsultSettingsReqDTO);
//        String result = cityService.getAllCities11(mergeConsultSettingsReqDTO);
        System.out.println("导数据-请求医生ID"+docId+"==请求返回结果"+result);
        return result;
    }

    @Test
    public void closeConsultData() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
//        String fileName = "C:\\Users\\Lance\\Desktop\\测试环境测试数据.xlsx";
        String fileName = "C:\\Users\\Lance\\Desktop\\关单.xlsx";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDiagnoseId());
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();

        AtomicInteger i = new AtomicInteger(1);
        docIds.parallelStream().forEach(d -> {
            CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
            closeConsultOrder.setDiagnoseId(d);
            System.out.println("关单参数"+JSON.toJSONString(closeConsultOrder));
            Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
            System.out.println("关单返回"+JSON.toJSONString(result));
            System.out.println("================================"+ i.getAndIncrement());
        });
//        for (String d: docIds) {
//            CloseConsultOrder closeConsultOrder = new CloseConsultOrder();
//            closeConsultOrder.setDiagnoseId(d);
//            System.out.println("关单参数"+JSON.toJSONString(closeConsultOrder));
//            Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
//            System.out.println("关单返回"+JSON.toJSONString(result));
//            System.out.println("================================"+ i.getAndIncrement());
////            try {
////                Thread.sleep(50);
////            } catch (InterruptedException e) {
////                System.out.println("异常");
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
        closeConsultOrder.setDiagnoseId("202212191444516730");
        System.out.println("关单参数"+JSON.toJSONString(closeConsultOrder));
        Result<Boolean> result = cityService.closeConsultOrder(closeConsultOrder);
        System.out.println("关单返回"+JSON.toJSONString(result));
    }

    @Test
    public void 修改医生价格(){

        String fileName = "C:\\Users\\Lance\\Desktop\\医生调价产品记录表(7).xlsx";
        ArrayList<SubmitConsultSettingsRespDTO> submitConsultSettingsRespDTOS = new ArrayList<>();

        EasyExcel.read(fileName, SubmitConsultSettingsExcelData.class, new PageReadListener<SubmitConsultSettingsExcelData>(dataList -> {
            for (SubmitConsultSettingsExcelData demoData : dataList) {
                try {
                    SubmitConsultSettingsRespDTO result = updateSubmitConsultSettings(demoData);
                    submitConsultSettingsRespDTOS.add(result);
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();

        System.out.println(submitConsultSettingsRespDTOS);
        // 写法1
        String fileNameresp = "C:\\Users\\Lance\\Downloads\\医生价格变更模板1 (6)" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileNameresp, SubmitConsultSettingsRespDTO.class).sheet("模板").doWrite(submitConsultSettingsRespDTOS);
        try {
            Thread.sleep(99999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testregister(){
        String fileName = "C:\\Users\\Lance\\Desktop\\用户.xlsx";
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getDiagnoseId());
                } catch (Exception e) {
                    System.out.println("数据解析异常");
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
            log.info("请求入参:{}",userRegisterReqDTO);
            Result<UserRegisterResDTO> register = cityService.register(userRegisterReqDTO);
            log.info("请求返回:{}",register);

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
        log.info("退款请求参数:{}",refundReqDTO);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM3MDc5MDc4OTUsInBheWxvYWQiOiJ7XCJpZFwiOjksXCJ1c2VybmFtZVwiOlwi5rWL6K-VLeWImOS8iuWHoVwiLFwibG9naW5OYW1lXCI6XCIxMzU4NTUwNDM4M1wiLFwidXNlclR5cGVcIjoxLFwiZG9jdG9ySWRcIjoxODY5ODIsXCJyb29tSWRcIjoxODY5ODIsXCJ0b2tlblwiOlwiZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SmxlSEFpT2pFMk56TTNNRGM1TURjNE9UUXNJbkJoZVd4dllXUWlPaUo3WENKcFpGd2lPakU0TmprNE1uMGlmUS5DaVVjSEYwdDJtcE5qdHZaNTBvLXdsb19nbWhZMWkzaXRvbFBqOGg4WE5vXCJ9In0.MFEODwPwugRfqyV4oC1HqWCPPQQrq6P75x3iMK1IrC8");
        String s = cityService.refundAndRepealWipedAssets(refundReqDTO,headers);
        log.info("退款返回结果:{}",s);
    }

    private SubmitConsultSettingsRespDTO updateSubmitConsultSettings(SubmitConsultSettingsExcelData demoData) {
        SubmitConsultSettingsRespDTO submitConsultSettingsRespDTO = new SubmitConsultSettingsRespDTO();
        try {
            SubmitConsultSettingsReqDTO reqDTO = convertConsultSettings(demoData);
            log.info("修改医生咨询价格请求参数:{}",reqDTO);
            Result<Boolean> result = cityService.submitConsultSettings(reqDTO);
            log.info("修改医生咨询价格返回结果:{}",result);
            if (result.isSuccess()) {
                submitConsultSettingsRespDTO.setRemark("成功");
            }else {
                submitConsultSettingsRespDTO.setRemark("失败");
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
            submitConsultSettingsRespDTO.setRemark("失败");
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
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
//        String fileName = "C:\\Users\\Lance\\Desktop\\测试环境测试数据.xlsx";
        String fileName = "C:\\Users\\19695\\Desktop\\815\\7W.csv";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
//                    docIds.add(demoData.getDocId());
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();


        List<List<String>> lists= Lists.partition(docIds,500);
        int n = 1;
        for (List<String> list :lists) {
            ArrayList<DemoData> dataaaaa = new ArrayList<DemoData>();
            //TODO 串行
            for (String docId:list) {
                DemoData demoData = new DemoData();
                try {
                    if (StringUtils.isBlank(docId)) {
                        continue;
                    }
                    Result<Boolean> test = test(docId);
//                    demoData.setDocId(docId);
                    demoData.setCallId(test.getCallId());
                    demoData.setSuccess(test.isSuccess()?"成功":"失败");
                    demoData.setResult(test.getResult()?"成功":"失败");
                    demoData.setErrorCode(test.getErrorCode());
                    demoData.setErrorMsg(test.getErrorMsg());
                    dataaaaa.add(demoData);
                } catch (Exception e) {
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("代码异常 执行错误"+e);
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    System.err.println("======================================================");
                    e.printStackTrace();
//                    demoData.setDocId(docId);
                    demoData.setException(e.toString());
                    dataaaaa.add(demoData);
                }
            }
            //TODO 并行
//            list.parallelStream().forEach(x->{
//                DemoData demoData = new DemoData();
//                try {
//                    Result<Boolean> test = test(x);
//                    demoData.setDocId(x);
//                    demoData.setCallId(test.getCallId());
//                    demoData.setSuccess(test.isSuccess()?"成功":"失败");
//                    demoData.setResult(test.getResult()?"成功":"失败");
//                    demoData.setErrorCode(test.getErrorCode());
//                    demoData.setErrorMsg(test.getErrorMsg());
//                    dataaaaa.add(demoData);
//                } catch (Exception e) {
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("代码异常 执行错误"+e);
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    System.err.println("======================================================");
//                    e.printStackTrace();
//                    demoData.setDocId(x);
//                    demoData.setException(e.toString());
//                    dataaaaa.add(demoData);
//                }
//            });
            // 写法1 JDK8+
            String s = UUID.randomUUID().toString();
            // since: 3.0.0-beta1
            String fileNameOut = "C:\\Users\\19695\\Desktop\\815\\result\\数据导入结果"+n+"=="+s+".xlsx";
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            EasyExcel.write(fileNameOut, DemoData.class).sheet("返回结果").doWrite(dataaaaa);
            n++;
        }

        try {
            Thread.sleep(99999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void simplecsvssRead() {
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
        String fileNameOut = "C:\\Users\\Lance\\Desktop\\sqlResp.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileNameOut, DemoData.class).sheet("返回结果").doWrite(data());


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
        String name = "北京";
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
        String cityName = "北京";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8");
        City city = createCity(cityName);
        ResultBean<City> mockCityResult = new ResultBean<City>(0, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        ResultBean<City> cityResultBean = cityService.getCityByName(cityName);
        assertEquals(mockCityResult, cityResultBean);

        // 测试如果返回值是 resultBean 时，尽管添加了 ResultBeanResponseProcessor 也不做特殊处理
        cityResultBean = cityServiceWithResultBeanResponseProcessor.getCityByName(cityName);
        assertEquals("返回值是 resultBean 时，ResultBeanResponseProcessor 不应做特殊处理", mockCityResult, cityResultBean);
    }

    @Test
    public void testWithConfigPathVariable() throws UnsupportedEncodingException {
        String cityName = "北京";
        String uri = "/city/getCityByName?name=" + URLEncoder.encode(cityName, "UTF-8") + "&id=";
        City city = createCity(cityName);
        ResultBean<City> mockCityResult = new ResultBean<City>(0, city);
        wireMockRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse().withBody(JSON.toJSONString(mockCityResult))));
        ResultBean<City> cityResultBean = cityService.getCityByNameWithConfigVariable();
        assertEquals(mockCityResult, cityResultBean);

        // 测试如果返回值是 resultBean 时，尽管添加了 ResultBeanResponseProcessor 也不做特殊处理
        cityResultBean = cityServiceWithResultBeanResponseProcessor.getCityByNameWithConfigVariable();
        assertEquals("返回值是 resultBean 时，ResultBeanResponseProcessor 不应做特殊处理", mockCityResult, cityResultBean);
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
        String name = "北京";
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
        String name = "北京";
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
                // 带了 header 的响应
                .willReturn(okJson(JSON.toJSONString(mockCities))
                        .withHeader(headerKey, headerValue)
                        .withHeader("Set-Cookie", cookie1)
                        .withHeader("Set-Cookie", cookie2)));
        HttpResponse response = cityService.listCity();
        System.out.println("获取到headers:" + response.getHeaders());
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
     * 测试表单提交
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
            // 没有返回值的方法，只要不报错就可以
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
        assertNull("当前方法ThreadLocal应及时清理", RequestPreprocessor.CURRENT_METHOD_THREAD_LOCAL.get());
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
        String cityName = "北京";
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
        String cityName = "北京";
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
                // 加在类上的头和cookie
                .withHeader("globalHeader1", equalTo("ok"))
                .withHeader("globalHeader2", equalTo("yes"))
                .withHeader("h3", equalTo("haha"))
                .withCookie("globalCookie", equalTo("ok"))
                // 类上的 UserAgent 注解
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
        String cityString = JSON.toJSONString(new ResultBean<String>(0, "北京"));
        wireMockRule.stubFor(get(urlPathEqualTo(uri))
                .withQueryParam("id", equalTo("1"))
                // 加在类上的头和cookie，如果key相同会被方法上的覆盖
                .withHeader("happy", equalTo("done"))
                .withHeader("h3", equalTo("nice"))
                .withHeader("globalHeader1", equalTo("ok"))
                .withHeader("globalHeader2", equalTo("yes"))
                .withCookie("globalCookie", equalTo("bad"))
                .withCookie("auth", equalTo("ok"))
                // 类上的 UserAgent 注解，会被方法上的注解覆盖
                .withHeader("User-Agent", equalTo("cityAgent"))
                .withHeader("Content-Type", equalTo("text/plain"))
                .willReturn(aResponse().withBody(cityString)));
        Object obj = cityService.getCityName(1);
        assertEquals(obj, cityString);
        obj = cityServiceWithResultBeanResponseProcessor.getCityName(1);
        assertEquals("北京", obj);
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
        String fileName = "C:\\Users\\Lance\\Downloads\\医生ID.xlsx";
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        ArrayList<String> docIds = new ArrayList<String>();
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                try {
                    docIds.add(demoData.getCallId());
                } catch (Exception e) {
                    System.out.println("数据解析异常");
                }
            }
        })).sheet().doRead();
        System.out.println(docIds);
        String sql = "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '是否有食物/药物过敏史或慢性疾病史，肝肾功能是否正常，是否长期用药史？如果没有，回复“无”。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '现在是否在月经期、备孕期、孕期、哺乳期等特殊时期？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '方便上传你的报告吗？以便了解你的病情。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '这种情况什么时间开始的，持续多久了？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '有没有家族内遗传病史，有没有类似疾病病史？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '目前精神状态如何？食欲怎么样？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '孩子几岁了，身高、体重分别是多少？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '之前有没有来医院就诊过，有没有相关诊断？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '有没有吸烟、喝酒、不健康生活史？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '除了这些症状，还有其他不舒服的地方吗？', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '目前这种情况可能需要到院做进一步的检查。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '需要什么药？可以把之前的药品照片或者药品名称发给我看一下。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '建议清淡饮食，忌食辛辣刺激、海鲜类等食物，适量运动，提高免疫力，保持心情舒缓，如有不适随诊。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '改善作息习惯，增强抵抗力，调整情绪，清淡饮食。', 0, 1, NULL, 'D', 0);\n" +
                "INSERT INTO `fosun_health`.`doc_doctor_shortcut_words` ( `create_time`, `update_time`, `user_id`, `content`, `weight`, `is_show`, `om_words_id`, `user_type`, `is_deleted`) VALUES ( '2022-12-07 11:01:30', '2022-12-07 11:01:30', 'DOCID', '不客气，感谢信任。', 0, 1, NULL, 'D', 0);\n";

        ArrayList<String> sqls = new ArrayList<>();
        for (String doctorId:docIds) {
            String docid = sql.replaceAll("DOCID", doctorId);
            sqls.add(docid);
        }

        FileUtils.FileWriteList("C:\\Users\\Lance\\Downloads\\sql2.text",sqls);
        System.out.println("");

    }


}