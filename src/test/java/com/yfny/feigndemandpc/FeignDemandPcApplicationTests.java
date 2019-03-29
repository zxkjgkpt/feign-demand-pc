package com.yfny.feigndemandpc;

import com.yfny.utilscommon.generator.entity.RequestInfo;
import com.yfny.utilscommon.generator.invoker.ConsumerInvoker;
import com.yfny.utilscommon.generator.invoker.TestInvoker;
import com.yfny.utilscommon.generator.invoker.base.Invoker;
import com.yfny.utilscommon.util.CodeInfoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignDemandPcApplicationTests {

    @Test
    public void contextLoads() {
        List<RequestInfo> requestInfoList = CodeInfoUtils.getRequestInfos();
        for (RequestInfo requestInfo : requestInfoList) {
            System.out.println("****************************************************");
            System.out.println("url: " + requestInfo.getUrl());
            System.out.println("methodName: " + requestInfo.getMethodName());
            System.out.println("requestMethod: " + requestInfo.getRequestMethod());
            String requestMethod = requestInfo.getRequestMethod();
            for (MethodParameter parameter : requestInfo.getRequestParams()) {
                System.out.println("requestParams: " + parameter.getParameter().getName() + "  requestParamsType: " + parameter.getParameter().getType());
            }
            System.out.println("****************************************************");
        }
    }

    @Test
    public void consumerInvokerTest() {
        Invoker invoker = new ConsumerInvoker.Builder()
                .setClassName("Xqdxx")
                .setDescription("需求单详情")
                .setApplicationName("service-demandform")
                .setFirst(true)
                .build();
        invoker.execute();
    }

    @Test
    public void initUnitTest() {
        Invoker invoker = new TestInvoker.Builder()
                .build();
        invoker.execute();
    }

}
