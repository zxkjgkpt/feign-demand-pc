package com.yfny.feigndemandpc.service;

import com.yfny.feigndemandpc.fallback.ActivityApiHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Activity工作流Service
 * <p>
 * Created  by  jinboYu  on  2019/4/15
 */
@FeignClient(url = "${feign.client.activity-url}", value = "activity-api", path = "/activityApi", fallback = ActivityApiHystrix.class)
public interface ActivityApiClient extends BaseClient {

    /**
     * 创建流程并完成第一个任务
     *
     * @param userId    流程发起人ID
     * @param key       流程ID
     * @param variables 流程变量
     * @return 下一个任务的ID
     */
    @PostMapping(value = "/task/create")
    Map<String,String> createTask(@RequestParam String userId, @RequestParam  String key, @RequestBody Map<String, Object> variables) throws Exception;

    /**
     * 创建流程并完成第一个任务,不设置流程变量
     *
     * @param userId    流程发起人ID
     * @param key       流程ID
     * @return 下一个任务的ID
     */
    @PostMapping(value = "/task/create/{userId}/{key}")
    String createTask(@PathVariable String userId, @PathVariable String key) throws Exception;


    /**
     * 完成流程任务
     *
     * @param taskId    任务ID
     * @param variables 流程变量
     * @return 返回下一个任务的ID
     */
    @PostMapping(value = "/task/fulfil/{taskId}")
    Map<String,String> fulfilTask(@PathVariable String taskId, @RequestBody Map<String, Object> variables) throws Exception;

    /**
     * 撤销流程
     *
     * @param taskId 流程任务ID
     * @return
     */
    @PostMapping(value = "/task/revocationTask/{taskId}")
    int revocationTask(@PathVariable String taskId) throws Exception;

    /**
     * 根据流程实例ID生成流程图，只高亮当前任务节点
     *
     * @param taskId   任务ID
     */
    @PostMapping(value = "/flowDiagram/getDiagram/{taskId}")
    String getDiagram(@PathVariable String taskId) throws Exception ;

    /**
     * 根据流程实例ID查询流程历史记录
     * @param processInstanceId     流程实例ID
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getHistories")
    List<Map<String,Object>> getHistories(String processInstanceId)throws Exception;

    /**
     * 根据任务ID查询当前任务历史记录
     * @param taskId    任务ID
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getHistoriesByTaskId")
    List<Map<String, Object>> getHistoriesByTaskId(@RequestParam String taskId) throws Exception;
}
