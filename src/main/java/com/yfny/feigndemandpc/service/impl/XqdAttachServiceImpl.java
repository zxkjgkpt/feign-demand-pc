package com.yfny.feigndemandpc.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.service.XqdFileBlocksService;
import com.yfny.feigndemandpc.service.XqdFileClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by jisongZhou on 2019/4/17.
 **/
@Service
public class XqdAttachServiceImpl {

    @Autowired
    private XqdFileClient xqdFileClient;

    @Autowired
    private XqdFileBlocksService xqdFileBlocksService;

    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public int xqdAttachUpload(String url, MultipartFile file, XqdFileBlocksEntity xqdFileBlocks) {

        int result = 0;
        XqdFileEntity xqdFile = new XqdFileEntity();

        String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        double fileSize = file.getSize();
        java.util.Date curDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(curDate.getTime());
        int blockNum = 0;
        int blockNo = 0;
        int fileState = 0;

        if ( blockNo == blockNum-1 ) {
            fileState = 1;
        } else if ( blockNo > 0 && blockNo < blockNum-1 ) {
            blockNo++;
        }

        xqdFileBlocks.setBlockId(uuid());
        xqdFileBlocks.setBlockSize(fileSize);
        xqdFileBlocks.setModifDate(date);
        xqdFileBlocks.setPath(url);
        xqdFileBlocks.setColumn9(url);

        xqdFile.setFileId(uuid());
        xqdFile.setFileMd5(xqdFileBlocks.getFileMd5());
        xqdFile.setBlockNo(new BigDecimal(blockNo));
        xqdFile.setFileName(fileName);
        xqdFile.setFileType(fileType);
        xqdFile.setFileSize(fileSize);
        xqdFile.setUserId("00");
        xqdFile.setGroupId("00");
        xqdFile.setModifDate(date);
        xqdFile.setFileState(new BigDecimal(fileState));

        xqdFileBlocksService.insertSelective(xqdFileBlocks);
        result = xqdFileClient.insertSelective(xqdFile);

        return result;
    }

    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public int xqdAttachDelete(String fileMd5) {
        XqdFileBlocksEntity xqdFileBlocksParam = new XqdFileBlocksEntity();
        XqdFileEntity xqdFileParam = new XqdFileEntity();

        xqdFileBlocksParam.setFileMd5(fileMd5);
        xqdFileParam.setFileMd5(fileMd5);

        xqdFileBlocksService.delete(xqdFileBlocksParam);
        int result = xqdFileClient.delete(xqdFileParam);

        return result;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
