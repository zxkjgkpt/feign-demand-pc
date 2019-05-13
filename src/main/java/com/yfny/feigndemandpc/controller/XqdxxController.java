package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.future.XqdxxFuture;
import com.yfny.feigndemandpc.service.ActivityApiClient;
import com.yfny.feigndemandpc.service.XqdxxClient;
import com.yfny.feigndemandpc.service.impl.XqdxxServiceImpl;
import com.yfny.utilscommon.basemvc.consumer.BaseController;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import com.yfny.utilscommon.util.ExcelUtil;
import com.yfny.utilscommon.util.InvokeResult;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求单详情Controller
 * Author auto
 * Date  2019-04-03
 */
@RestController
@RequestMapping(value = "/xqdxx")
public class XqdxxController extends BaseController<XqdxxEntity> {

    @Autowired
    private ActivityApiClient activityApiClient;

    @Autowired
    private XqdxxServiceImpl xqdxxServiceImpl;

    @Autowired
    private XqdxxClient xqdxxClient;

    @Autowired
    private XqdxxFuture xqdxxFuture;

    @Override
    public BaseFuture<XqdxxEntity> getBaseFuture() {
        return this.xqdxxFuture;
    }

    /**
     * 导出需求单
     *
     * @param xqdxx 对象实体
     * @return 返回成功或者失败
     */
    @PostMapping(value = "/exportXqd")
    @ResponseBody
    public void exportXqd(@RequestBody XqdxxEntity xqdxx, HttpServletRequest request, HttpServletResponse response) {

        List<XqdxxEntity> result = xqdxxClient.findSimpleListByAndCondition(xqdxx);

        if (result != null) {

            //初始化数据
            result = initData(result);

            String fileName = null;
            if (xqdxx.getXqdType().equals("xqtb")) {
                fileName = "需求单填报";
            } else {
                fileName = "需求单总览";
            }

            //创建workbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建HSSFSheet对象
            HSSFSheet xlsxSheet = wb.createSheet(fileName);

            //从第三行起开始写入数据
            int writeRow = 2;

            for (XqdxxEntity sence : result) {
                //创建写入数据行
                xlsxSheet.createRow(writeRow);
                //需求单状态
                xlsxSheet.getRow(writeRow).createCell(0).setCellValue(sence.getGdzt());
                //需求单号
                xlsxSheet.getRow(writeRow).createCell(1).setCellValue(sence.getXqdh());
                //申请单位/业务部门
                xlsxSheet.getRow(writeRow).createCell(2).setCellValue(sence.getSqbmmc());
                //申请人
                xlsxSheet.getRow(writeRow).createCell(3).setCellValue(sence.getSqrxm());
                //申请人联系方式
                xlsxSheet.getRow(writeRow).createCell(4).setCellValue(sence.getSqrlxfs());
                //创建时间
                xlsxSheet.getRow(writeRow).createCell(5).setCellValue(sence.getCjsj());
                //需求单名称
                xlsxSheet.getRow(writeRow).createCell(6).setCellValue(sence.getXqmc());
                //需求单综述
                xlsxSheet.getRow(writeRow).createCell(7).setCellValue(sence.getXqzs());
                //期望完成时间
                xlsxSheet.getRow(writeRow).createCell(8).setCellValue(sence.getQwwcsj());
                //地审核人
                xlsxSheet.getRow(writeRow).createCell(9).setCellValue(sence.getDshr());
//                //省审核人
//                xlsxSheet.getRow(writeRow).createCell(10).setCellValue(sence.getSshr());
//                //网审核人
//                xlsxSheet.getRow(writeRow).createCell(11).setCellValue(sence.getWshr());

                writeRow++;
            }

            //导出Excel
            ExcelUtil.exportExcel(wb, xlsxSheet, request, response, fileName);
        }
    }


    /**
     * 初始化数据
     *
     * @param result 对象实体
     * @return 对象实体列表
     */
    public List<XqdxxEntity> initData(List<XqdxxEntity> result) {

        for (XqdxxEntity xqdxxEntity : result) {
            //初始化状态
            switch (xqdxxEntity.getGdzt()) {
                case "New":
                    xqdxxEntity.setGdzt("新建");
                    break;
//                case "ProAudit":
//                    xqdxxEntity.setGdzt("省级审核中");
//                    break;
//                case "ProModif":
//                    xqdxxEntity.setGdzt("省级审核未通过");
//                    break;
//                case "PowerAudit":
//                    xqdxxEntity.setGdzt("网级审核中");
//                    break;
//                case "PowerModif":
//                    xqdxxEntity.setGdzt("网级审核未通过");
//                    break;
//                case "Pass":
//                    xqdxxEntity.setGdzt("审核通过");
//                    break;
//                case "ProCancel":
//                    xqdxxEntity.setGdzt("作废申请-省级审核中");
//                    break;
//                case "PowerCancel":
//                    xqdxxEntity.setGdzt("作废申请-网级审核中");
//                    break;
//                case "Cancel":
//                    xqdxxEntity.setGdzt("已作废");
//                    break;
            }
        }

        return result;
    }


    /**
     * 新建需求单
     *
     * @param xqdxxEntity 需求单实体
     * @return
     * @throws Exception
     */
    @PostMapping("/newXqd")
    public InvokeResult newXqd(@RequestBody XqdxxEntity xqdxxEntity) throws Exception {
        int result = xqdxxServiceImpl.newXqd(xqdxxEntity);
        if (result == 1) {
            return InvokeResult.success("20001", result);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure("20002");
    }

    /**
     * 提交需求单
     *
     * @param xqdxxEntity 需求单实体
     * @return
     * @throws Exception
     */
    @PostMapping("/submitXqd")
    public InvokeResult submitXqd(@RequestBody XqdxxEntity xqdxxEntity) throws Exception {
        int result = xqdxxServiceImpl.submitXqd(xqdxxEntity);
        if (result == 1) {
            return InvokeResult.success("20005", xqdxxEntity);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure("20006");
    }

    /**
     * 查看流程图
     *
     * @param taskId 流程任务ID
     * @throws Exception
     */
    @PostMapping(value = "/getDiagram/{taskId}")
    public void getDiagram(@PathVariable String taskId, HttpServletResponse response) throws Exception {
        String base64 = activityApiClient.getDiagram(taskId);
        if (base64 != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    //调成异常数据
                    b[i] += 256;
                }
            }
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            out.write(b);
            out.flush();
            out.close();
        }
    }

    /**
     * 审核需求单
     *
     * @param xqdxxEntity 需求单实体
     * @return
     * @throws Exception
     */
    @PostMapping("/auditXqd")
    public InvokeResult auditXqd(@RequestBody XqdxxEntity xqdxxEntity) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (xqdxxEntity.getGateway() != null) {
            map.put("Gateway", xqdxxEntity.getGateway());
        }
        if (xqdxxEntity.getSftg() != null && xqdxxEntity.getSftg() == 1) {
            xqdxxEntity.setSfycgd(new Long(0));
        } else {
            xqdxxEntity.setSfycgd(new Long(1));
        }
        if (xqdxxEntity.getSupplement() != null) {
            map.put("isSupplement", xqdxxEntity.getSupplement());
            if (!xqdxxEntity.getSupplement()) {
                xqdxxEntity.setSfycgd(new Long(0));
            } else {
                xqdxxEntity.setSfycgd(new Long(1));
            }
        }
        map.put("pass", xqdxxEntity.getSftg() == 1 ? false : true);
        int result = xqdxxServiceImpl.auditXqd(xqdxxEntity, map);
        if (result == 1) {
            return InvokeResult.success("20007", xqdxxEntity);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure("20008");

    }

    /**
     * 撤销需求单
     *
     * @param taskId 流程任务Id
     * @return
     * @throws Exception
     */
    @PostMapping("/cancelXqd/{taskId}")
    public InvokeResult cancelXqd(@PathVariable String taskId) throws Exception {
        int result = activityApiClient.revocationTask(taskId);
        if (result == 1) {
            return InvokeResult.success("20001", result);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure("20002");
    }

    /**
     * 需求单申请作废
     *
     * @param xqdxxEntity 需求单实体
     * @return
     * @throws Exception
     */
    @PostMapping("/cancelApply")
    public InvokeResult cancelApply(@RequestBody XqdxxEntity xqdxxEntity) throws Exception {
        int result = xqdxxServiceImpl.cancelApply(xqdxxEntity);
        if (result == 1) {
            return InvokeResult.success("20005", xqdxxEntity);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure("20006");
    }

    /**
     * 根据流程实例ID查询历史记录
     * @param processInstanceId     流程实例ID
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getHistories")
    public InvokeResult getHistories(String processInstanceId)throws Exception{
        List<Map<String, Object>> histories = activityApiClient.getHistories(processInstanceId);
        if (histories!=null){
            return InvokeResult.success("20001",histories);
        }else {
            return InvokeResult.failure("20002");
        }
    }

    /**
     * 根据任务ID查询当前任务历史记录
     * @param taskId    任务ID
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getHistoriesByTaskId")
    public InvokeResult getHistoriesByTaskId(@RequestParam String taskId) throws Exception{
        List<Map<String, Object>> histories = activityApiClient.getHistoriesByTaskId(taskId);
        if (histories!=null){
            return InvokeResult.success("20001",histories);
        }else {
            return InvokeResult.failure("20002");
        }
    }
}
