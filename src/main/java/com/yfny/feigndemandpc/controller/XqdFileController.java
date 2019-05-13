package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.future.XqdxxFileFuture;
import com.yfny.feigndemandpc.service.XqdFileBlocksService;
import com.yfny.feigndemandpc.service.impl.XqdAttachServiceImpl;
import com.yfny.feigndemandpc.utils.FastDFSFile;
import com.yfny.feigndemandpc.utils.FastDFSUtils;
import com.yfny.utilscommon.basemvc.consumer.BaseController;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * 需求单附件Controller
 * Author auto
 * Date  2019-04-10
 */
@RestController
@RequestMapping(value = "/xqdFile")
public class XqdFileController extends BaseController<XqdFileEntity> {

    @Autowired
    private XqdFileBlocksService xqdFileBlocksService;

    @Autowired
    private XqdAttachServiceImpl xqdAttachService;

    @Autowired
    private XqdxxFileFuture xqdxxFileFuture;

    @Override
    public BaseFuture<XqdFileEntity> getBaseFuture() {
        return this.xqdxxFileFuture;
    }

    @PostMapping(value = "/xqdFileUpload")
    public InvokeResult xqdFileUpload(@RequestParam(value = "file") MultipartFile file, XqdFileBlocksEntity xqdFileBlocks) throws Exception {
        FastDFSFile newFile = new FastDFSFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
        String url = FastDFSUtils.uploadFile(newFile);
        int result = xqdAttachService.xqdAttachUpload(url, file, xqdFileBlocks);
        return InvokeResult.writeResult(result, "20001", "10003", "20002");
    }

    @GetMapping(value = "/xqdFileDownload/{fileMd5}/{fileName}")
    public void xqdFileDownload(@PathVariable("fileMd5") String fileMd5, @PathVariable("fileName") String fileName, HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream outputStream = null;
        XqdFileBlocksEntity xqdFileBlocks = new XqdFileBlocksEntity();
        xqdFileBlocks.setFileMd5(fileMd5);
        xqdFileBlocks = xqdFileBlocksService.selectOne(xqdFileBlocks);
        String filePath = xqdFileBlocks.getPath();
        String file = FastDFSUtils.getFileNameFormFilePath(filePath);
        String group = FastDFSUtils.getGroupFormFilePath(filePath);
        byte[] bytes = FastDFSUtils.download_file(group, file);
        httpServletResponse.reset();// 清空输出流
        httpServletResponse.setCharacterEncoding("UTF-8");//　此处很重要，否则下载的文件会乱码损坏打不开
        // 此处必须设置编码，否则无法下载
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        httpServletResponse.setContentType("application/msexcel");// 定义输出类型
        try {
            outputStream = httpServletResponse.getOutputStream();
            if (outputStream != null) {
                outputStream.write(bytes);
            }
        } finally {
            httpServletResponse.setStatus(200);
            if (outputStream != null) {
                // 输出文件到前端后关闭流
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    @PostMapping(value = "/xqdFileDelete/{fileMd5}")
    public InvokeResult xqdFileDelete(@PathVariable("fileMd5") String fileMd5) throws Exception {
        XqdFileBlocksEntity xqdFileBlocks = new XqdFileBlocksEntity();
        xqdFileBlocks.setFileMd5(fileMd5);
        xqdFileBlocks = xqdFileBlocksService.selectOne(xqdFileBlocks);
        String filePath = xqdFileBlocks.getPath();
        String file = FastDFSUtils.getFileNameFormFilePath(filePath);
        String group = FastDFSUtils.getGroupFormFilePath(filePath);
        FastDFSUtils.delete_file(group, file);
        int result = xqdAttachService.xqdAttachDelete(fileMd5);
        return InvokeResult.writeResult(result, "20003", "10003", "20004");
    }

}
