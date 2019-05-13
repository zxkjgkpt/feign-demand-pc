package com.yfny.feigndemandpc.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.ActivityApiClient;
import com.yfny.feigndemandpc.service.QuartzApiClient;
import com.yfny.feigndemandpc.service.XqdxxClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Created  by  jinboYu  on  2019/4/18
 */
@Service
public class XqdxxServiceImpl {

    @Autowired
    private ActivityApiClient activityApiClient;

    @Autowired
    private XqdxxClient xqdxxClient;

    private QuartzApiClient quartzApiClient;

    /**
     * 新建需求单
     * @param xqdxxEntity
     * @return
     * @throws Exception
     */
    @Transactional
    @LcnTransaction
    public int newXqd(XqdxxEntity xqdxxEntity) throws Exception {
        //设置返回值
        int result = 0;
        //保存需求单不创建流程任务
        result = xqdxxClient.insertSelective(xqdxxEntity);
        return result;
    }

    /**
     * 提交需求单
     * @param xqdxxEntity
     * @return
     * @throws Exception
     */
    @Transactional
    @LcnTransaction
    public int submitXqd(XqdxxEntity xqdxxEntity) throws Exception {
        //设置返回值
        int result = 0;
        //提交需求单需创建流程任务
        Map<String, Object> map = new HashMap<>();
        map.put("zzId", xqdxxEntity.getZzid());
        //先创建任务流程
        Map<String, String> taskMap = activityApiClient.createTask(xqdxxEntity.getCjrid(), "demand", map);
        if (taskMap != null) {
            xqdxxEntity.setTaskId(taskMap.get("taskId"));
            xqdxxEntity.setGdzt(taskMap.get("taskName"));
            //先查询数据存不存在,如存在执行更新操作
            if (xqdxxClient.selectByPrimaryKey(xqdxxEntity.getXqdh()) != null) {
                result = xqdxxClient.updateSelective(xqdxxEntity);
            } else {
                //否则执行新增操作
                result = xqdxxClient.insertSelective(xqdxxEntity);
                return result;
//            if (result == 1) {
//                Calendar calendar = Calendar.getInstance();
//                //获取当前时间时分秒
//                int hour = calendar.get(Calendar.HOUR_OF_DAY);//时
//                int minute = calendar.get(Calendar.MINUTE);//分
//                int second = calendar.get(Calendar.SECOND);//秒
//                //获取需求单审核停留时间
//                Long shtlsj = xqdxxEntity.getShtlsj();
//                hour = hour - 8;
//                if (hour<0){
//                    shtlsj = shtlsj - 1;
//                    hour = 24 - hour;
//                }
//                //设置cron表达式
//                String cron = second + " " + minute + " " + hour + " */" + shtlsj + " * ?";
//                xqdxxEntity.setCron(cron);
//                System.out.println(cron);
//                return quartzApiClient.add(xqdxxEntity);
//            }else {
//                return result;
//            }
            }
            return result;
        } else {
            return result;
        }
    }


    /**
     * 审核需求单
     * @param xqdxxEntity   需求单实体
     * @param map           变量设置
     * @return
     * @throws Exception
     */
    @Transactional
    @LcnTransaction
    public int auditXqd(XqdxxEntity xqdxxEntity,Map<String,Object> map) throws Exception {
        int result = 0;
        Map<String,String> taskMap = activityApiClient.fulfilTask(xqdxxEntity.getTaskId(),map);
        if (taskMap!=null){
            String thisTaskId = xqdxxEntity.getTaskId();
            String thisGdzt = xqdxxEntity.getGdzt();
            xqdxxEntity.setTaskId(taskMap.get("taskId"));
            xqdxxEntity.setGdzt(taskMap.get("taskName"));
            result = xqdxxClient.updateSelective(xqdxxEntity);
            xqdxxEntity.setTaskId(thisTaskId);
            xqdxxEntity.setGdzt(thisGdzt);
            return result;
        }else {
           return result;
        }
    }


    /**
     * 需求单申请作废
     * @param xqdxxEntity
     * @return
     * @throws Exception
     */
    @Transactional
    @LcnTransaction
    public int cancelApply(XqdxxEntity xqdxxEntity) throws Exception {
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        if (xqdxxEntity.getGateway() != null)
        {
            map.put("zzId",xqdxxEntity.getZzid());
            map.put("Gateway", xqdxxEntity.getGateway());
        }else {
            return result;
        }
        Map<String,String> taskMap = activityApiClient.fulfilTask(xqdxxEntity.getTaskId(),map);
        if (taskMap!=null){
            xqdxxEntity.setTaskId(taskMap.get("taskId"));
            xqdxxEntity.setGdzt(taskMap.get("taskName"));
            result = xqdxxClient.updateSelective(xqdxxEntity);
            return result;
        }else {
            return result;
        }
    }

    /**
     * 根据流程实例ID查询历史记录
     * @param processInstanceId     流程实例ID
     * @return
     * @throws Exception
     */
    @Transactional
    @LcnTransaction
    public List<Map<String,Object>> getHistories(String processInstanceId)throws Exception{
        List<Map<String, Object>> historiesList = activityApiClient.getHistories(processInstanceId);
        if (historiesList!=null){
            return historiesList;
        }else {
            return null;
        }
    }

    List<Map<String, Object>> getHistoriesByTaskId(@RequestParam String taskId) throws Exception{
        List<Map<String, Object>> histories = activityApiClient.getHistoriesByTaskId(taskId);
        if (histories!=null){
            return histories;
        }else {
            return null;
        }
    }

}
