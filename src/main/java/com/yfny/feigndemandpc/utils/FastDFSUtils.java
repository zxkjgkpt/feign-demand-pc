package com.yfny.feigndemandpc.utils;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * FastDFS工具类
 * Created by zileShi on 2018/2/26.
 **/
public class FastDFSUtils implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);
    /**
     *
     */
    private static final long serialVersionUID = -4462272673174266738L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient1 storageClient1;
    static {
        try {
            //clientGloble读配置文件
            ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
            ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
            //trackerclient
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            //storageclient
            storageClient1 = new StorageClient1(trackerServer, null);
        } catch (Exception e) {
            logger.error("连接超时，请查看IP地址是否正确");
            e.printStackTrace();
        }
    }

    /**
     * fastDFS文件上传
     * <p>
     * 注意：
     * 调用上传功能需要解析web传来的file
     * 直接复制下面这句话
     * //解析file
     * FastDFSFile fastDFSFile = new FastDFSFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
     * 再执行上传功能
     *
     * @param file 上传的文件 FastDFSFile
     * @return String 返回文件的绝对路径
     */
    public static String uploadFile(FastDFSFile file) {

        String path = null;
        try {
            if (file != null) {
                //文件扩展名
                String ext = FilenameUtils.getExtension(file.getName());
                //mata list是表文件的描述
//                NameValuePair[] mata_list = new NameValuePair[3];
//                mata_list[0] = new NameValuePair("fileName", file.getName());
//                mata_list[1] = new NameValuePair("fileExt", ext);
//                mata_list[2] = new NameValuePair("fileSize", String.valueOf(file.getSize()));
                path = storageClient1.upload_file1(file.getContent(), ext, null);
            }
        } catch (Exception e) {
            //返回请求失败码
            return "请求失败!";
        }
        return path;
    }
    public static String uploadFile(String filePath) {

        String path = null;
        try {
            if (filePath != null) {
                path = storageClient1.upload_file1(filePath, null, null);
            }
        } catch (Exception e) {
            //返回请求失败码
            return "请求失败!";
        }
        return path;
    }

    /**
     * 从存储服务器下载文件
     *
     * @param group_name      存储服务器组名
     * @param remote_filename 存储服务器上的文件名
     * @return 0:成功，否则，失败
     * @throws IOException
     * @throws MyException
     */
    public static byte[] download_file(String group_name, String remote_filename) {
        //请求失败码
        byte[] result;
        try {
            result = storageClient1.download_file(group_name, remote_filename);
        } catch (Exception e) {
            //请求失败
            result = new byte[]{};
        }
        return result;
    }

    /**
     * 从存储服务器下载文件
     *
     * @param group_name      存储服务器组名
     * @param remote_filename 存储服务器上的文件名
     * @return 0:成功，否则，失败
     * @throws IOException
     * @throws MyException
     */
    public static int download_file2(String group_name, String remote_filename) {
        //请求失败码
        int errorNumber = -1;

        try {
            //在本地C盘创建文件
            String local_filename = "c:\\downLoad";
            //如果不存在，则创建，反之
            if (!new File(local_filename).isDirectory()) {
                new File(local_filename).mkdirs();
            }
            //为文件名添加时间，防止文件重名
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            //装配文件下载的路径
            local_filename = "c:\\downLoad\\" + sf.format(new Date())+remote_filename.replaceAll("/", "-");
            //执行下载操作
            errorNumber = storageClient1.download_file(group_name, remote_filename, local_filename);
        } catch (Exception e) {
            //请求失败
            return errorNumber;
        }

        return errorNumber;
    }

    /**
     * 从存储服务器删除文件
     *
     * @param group_name      存储服务器组名
     * @param remote_filename 存储服务器上的文件名
     * @return 0:成功，否则，失败
     * @throws IOException
     * @throws MyException
     */
    public static int delete_file(String group_name, String remote_filename) {
        //请求失败码
        int errorNumber = -1;

        try {
            //执行删除操作，并返回错误码
            errorNumber = storageClient1.delete_file(group_name, remote_filename);
        } catch (Exception e) {
            //请求失败
            return errorNumber;
        }
        return errorNumber;
    }


    /**
     * 根据fastDFS返回的path得到文件的组名
     *
     * @param path fastDFS返回的path
     * @return
     */
    public static String getGroupFormFilePath(String path) {
        return path.split("/")[0];
    }

    /**
     * 根据fastDFS返回的path得到文件名
     *
     * @param path fastDFS返回的path
     * @return
     */
    @NotNull
    public static String getFileNameFormFilePath(String path) {
        return path.substring(path.indexOf("/") + 1);
    }
}
