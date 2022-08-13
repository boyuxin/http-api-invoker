package com.github.dadiyang.httpinvoker.interfaces;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.github.dadiyang.httpinvoker.entity.DemoData;
import com.github.dadiyang.httpinvoker.entity.MergeConsultSettingsReqDTO;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * 读取转换异常
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class DemoExceptionListener implements ReadListener<DemoData> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);




    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    ArrayList<String> docIds = new ArrayList<String>();
    ArrayList<DemoData> dataaaaa = new ArrayList<DemoData>();

    private static final int PORT = 18888;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(PORT));
    @Autowired
    private CityService cityService;

    @Override

    public void invoke(DemoData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        docIds.add(data.getDocId());
        String docId = data.getDocId();
        String resp = "test(docId);";
//        data.setResp(resp);
        dataaaaa.add(data);
    }

//    public String test(String docId) {
//        MergeConsultSettingsReqDTO mergeConsultSettingsReqDTO = new MergeConsultSettingsReqDTO();
//        HashSet<String> strings = new HashSet<String>();
//        strings.add(docId);
//        mergeConsultSettingsReqDTO.setDoctorIds(strings);
////        String allCities = cityService.getAllCities11(mergeConsultSettingsReqDTO);
//        System.out.println(allCities);
//        return allCities;
//    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        String fileNameOut = "C:\\Users\\Lance\\Desktop\\sqlResp.xlsx";
        EasyExcel.write(fileNameOut, DemoData.class).sheet("返回结果").doWrite(docIds);

        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
