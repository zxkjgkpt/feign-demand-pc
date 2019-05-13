//package com.yfny.feigndemandpc.utils;
//
//import com.aspose.words.Document;
//import com.aspose.words.FontSettings;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
//import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
//import com.yfny.servicedemandform.service.XqdFileBlocksServiceImpl;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//
///**
// * Office文件转换成pdf
// * @author huqinggang
// *
// */
//@Component
//public class AspOfficeToPDF extends Thread implements ApplicationContextAware
//{
//
//    private static ApplicationContext applicationContext = null;
//
//    @Autowired
//    private XqdFileBlocksService xqdFileBlocksService;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        if(AspOfficeToPDF.applicationContext == null){
//            AspOfficeToPDF.applicationContext  = applicationContext;
//        }
//    }
//
//    //获取applicationContext
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    //通过name获取 Bean.
//    public static Object getBean(String name){
//        return getApplicationContext().getBean(name);
//
//    }
//
//    //通过class获取Bean.
//    public static <T> T getBean(Class<T> clazz){
//        return getApplicationContext().getBean(clazz);
//    }
//
//    //通过name,以及Clazz返回指定的Bean
//    public static <T> T getBean(String name,Class<T> clazz){
//        return getApplicationContext().getBean(name, clazz);
//    }
//
//	private Logger logger = LogManager.getLogger(AspOfficeToPDF.class);
//	private String docPath;
//	private String pdfPath;
//	private String blockId;
//	private String path;
//	private String fastdfsPath;
//
//
//	public AspOfficeToPDF(){
//		super();
//	}
//
//	public AspOfficeToPDF(String path, String docPath, String pdfPath, String fastdfsPath, String blockId){
//		super();
//		this.docPath=docPath;
//		this.pdfPath=pdfPath;
//		this.blockId = blockId;
//		this.path = path;
//		this.fastdfsPath = fastdfsPath;
//	}
//
//	@Override
//	public void run(){
//		doc2PDF(path,docPath, pdfPath, fastdfsPath, blockId);
//	}
//
//    /**
//     * 获取license
//     *
//     * @return
//     */
//    public  boolean getLicense() {
//        boolean result = false;
//        try {
//            InputStream is = AspOfficeToPDF.class.getClassLoader().getResourceAsStream("license.xml");
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 判断是否为空
//     * @param obj
//     *            字符串对象
//     * @return
//     */
//    protected  boolean notNull(String obj) {
//        if (obj != null && !obj.equals("") && !obj.equals("undefined")
//                && !obj.trim().equals("") && obj.trim().length() > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * doc to pdf
//     *
//     * @param docPath
//     *            doc源文件
//     * @param pdfPath
//     *            pdf目标文件
//     */
//    public  boolean doc2PDF(String path,String docPath, String pdfPath, String fastdfsPath, String blockId)
//    {
//    	boolean zt=true;
//        try {
//        	docPath=java.net.URLDecoder.decode(docPath, "UTF-8");
//        	pdfPath=java.net.URLDecoder.decode(pdfPath, "UTF-8");
//            // 验证License
//            if (!getLicense())
//            {
//                return false;
//            }
//            if (notNull(docPath) && notNull(pdfPath))
//            {
//                File file = new File(pdfPath);
//                String osName = System.getProperties().getProperty("os.name");
//                //判断当前系统，如果不是Window就加中文编码
//                if (!osName.contains("Window")){
//                    FontSettings.setFontsFolder("/usr/share/fonts/", true);
//                }
//                //FontSettings.setFontsFolder("/usr/share/fonts/", true);
//                FileOutputStream os = new FileOutputStream(file);
//                Document doc = new Document(docPath);
//                doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
//                file.exists();
//                os.close();
//                String column9 = FastDFSUtils.uploadFile(pdfPath);
//                System.out.println("column9 = " + column9);
//                XqdFileBlocksEntity xqdFileBlocks = new XqdFileBlocksEntity();
//                xqdFileBlocks.setBlockId(blockId);
//                xqdFileBlocks.setColumn9(fastdfsPath + column9);
////                IXtglService iXtglService = (IXtglService) AspOfficeToPDF.getBean("XtgnService");
////                int row = iXtglService.updateByPrimaryKeySelective(xqdFileBlocks);
//                int row = xqdFileBlocksService.updateSelective(xqdFileBlocks);
//                if (row != 0){
//                    zt= true;
//                    delAllFile(path);
//                }
//                else {
//                    return false;
//                }
//
//            }
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            zt=false;
//        }
//        return zt;
//    }
//
//
//
//    public void delFolder(String folderPath) {
//        try {
//            delAllFile(folderPath); //删除完里面所有内容
//            String filePath = folderPath;
//            filePath = filePath.toString();
//            File myFilePath = new File(filePath);
//            myFilePath.delete(); //删除空文件夹
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean delAllFile(String path) {
//        boolean flag = false;
//        File file = new File(path);
//        if (!file.exists()) {
//            return flag;
//        }
//        if (!file.isDirectory()) {
//            return flag;
//        }
//        String[] tempList = file.list();
//        File temp = null;
//        for (int i = 0; i < tempList.length; i++) {
//            if (path.endsWith(File.separator)) {
//                temp = new File(path + tempList[i]);
//            } else {
//                temp = new File(path + File.separator + tempList[i]);
//            }
//            if (temp.isFile()) {
//                temp.delete();
//            }
//            if (temp.isDirectory()) {
//                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
//                delFolder(path + "/" + tempList[i]);//再删除空文件夹
//                flag = true;
//            }
//        }
//        return flag;
//    }
//}
//
//
//	/*
//	public static void main(String[] args)
//	{
//		AspOfficeToPDF word = new AspOfficeToPDF();
//      word.doc2PDF("D:\\新建 Microsoft Word 文档.docx", "D:\\新建 Microsoft Word 文档_" + new Date().getTime() + ".pdf");
//
//	}*/
//
