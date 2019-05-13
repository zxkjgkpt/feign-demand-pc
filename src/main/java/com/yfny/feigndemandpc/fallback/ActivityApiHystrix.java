package com.yfny.feigndemandpc.fallback;

import com.yfny.feigndemandpc.service.ActivityApiClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Activity工作流Hystrix
 * <p>
 * Created  by  jinboYu  on  2019/4/15
 */
@Component
public class ActivityApiHystrix extends BaseHystrix implements ActivityApiClient {
    /**
     * 创建流程并完成第一个任务
     *
     * @param userId    流程发起人ID
     * @param key       流程ID
     * @param variables 流程变量
     * @return 下一个任务的ID
     */
    @Override
    public Map<String,String> createTask(String userId, String key, Map<String, Object> variables) throws Exception {
        return null;
    }

    /**
     * 创建流程并完成第一个任务,不设置流程变量
     *
     * @param userId 流程发起人ID
     * @param key    流程ID
     * @return 下一个任务的ID
     */
    @Override
    public String createTask(String userId, String key) throws Exception {
        return null;
    }

    /**
     * 完成流程任务
     *
     * @param taskId    任务ID
     * @param variables 流程变量
     * @return 返回下一个任务的ID
     */
    @Override
    public Map<String,String> fulfilTask(String taskId, Map<String, Object> variables) throws Exception {
        return null;
    }

    /**
     * 撤销流程
     *
     * @param taskId 流程任务ID
     * @return
     */
    @Override
    public int revocationTask(String taskId) throws Exception {
        return -1;
    }

    /**
     * 根据流程实例ID生成流程图，只高亮当前任务节点
     *
     * @param taskId   任务ID
     */
    @Override
    public String getDiagram(String taskId) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> getHistories(String processInstanceId) throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> getHistoriesByTaskId(String taskId) throws Exception {
        return null;
    }
}
