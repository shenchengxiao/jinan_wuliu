package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.exception.YCException;
import com.manager.handler.BannerHandler;
import com.manager.pojo.BannerInfo;
import com.manager.request.BaseQuery;
import com.manager.response.BannerInfoResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.IdGenerator;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;
import java.util.Map;


/**
 * Created by shencx on 2017/4/1.
 */
@Controller
@RequestMapping(value = "/api/banner")
@Authentication(allow = UserRoleEnum.SuperAdmin)
public class BannerController {

    @Resource
    private BannerHandler bannerHandler;

    Logger LOG = LoggerFactory.getLogger(BannerController.class);

    /**
     * 添加banner
     * @param request
     * @param file
     * @param bannerInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editor",method = RequestMethod.POST)
    public APIResponse editor(HttpServletRequest request, File file, BannerInfo bannerInfo){
        APIResponse apiResponse = new APIResponse();
        try {
            bannerHandler.addBanner(bannerInfo,file,request);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("editor 发生异常",bannerInfo);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取列表
     * @param request
     * @param baseQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<Page<BannerInfoResponse>> list(HttpServletRequest request, BaseQuery baseQuery){
        APIResponse<Page<BannerInfoResponse>> apiResponse = new APIResponse<>();
        Page<BannerInfoResponse> page = null;

        try {
            page = bannerHandler.getBannerList(baseQuery);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (YCException e) {
            LOG.error("list 发生异常",baseQuery);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }


    /**
     * 图片上传
     * @param
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload")
    public FileUpload uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
//        try {
//            InputStream in = new FileInputStream(imageFile);
//            String dir = request.getSession().getServletContext().getRealPath("/upload");
//            File fileLocation = new File(dir);
//            //此处也可以在应用根目录手动建立目标上传目录
//            if(!fileLocation.exists()){
//                boolean isCreated  = fileLocation.mkdir();
//                if(!isCreated) {
//                    //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
//                    return null;
//                }
//            }
//
//            //获取图片后缀类型
//            String suffix = imageFile.getName().substring(imageFile.getName().lastIndexOf(".") + 1);
//            //生成一个32位随机码
//            String fileName = IdGenerator.getUUIDHex32()+suffix;
//
//            System.out.println(fileName);
//            File uploadFile = new File(dir, fileName);
//            OutputStream out = new FileOutputStream(uploadFile);
//            byte[] buffer = new byte[1024 * 1024];
//            int length;
//            while ((length = in.read(buffer)) > 0) {
//                out.write(buffer, 0, length);
//            }
//            in.close();
//            out.close();
//            return fileName;
//        } catch (FileNotFoundException ex) {
//            return null;
//        } catch (IOException ex) {
//            return null;
//        }
//    }

        FileUpload fileUploadRs = new FileUpload();
        InputStream in = null;
        FileOutputStream os = null;
        try {
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (String filekey : fileMap.keySet()) {
                MultipartFile patch = request.getFile(filekey);
                if (patch != null) {
                    // 上传的文件名
                    String uploadFileName = patch.getOriginalFilename();
                    // 获取文件后缀名
                    String fileType = StringUtils.substringAfterLast(uploadFileName, ".");
                    // 生成唯一文件名称
                    String uuidFileName = StringUtils.lowerCase(IdGenerator.getUUIDHex32());
                    //文件类型判断 是否可以上传
                    boolean isUpload = false;

                    if (isUpload) {
                        //文件上传位置
                        String saveFilePath = request.getSession().getServletContext().getRealPath("/upload");
                        File dirTempFile = new File(saveFilePath);
                        final String physicalPath = saveFilePath + "/" + uuidFileName + "." + fileType;
                        try {
                            if (!dirTempFile.exists())
                                dirTempFile.mkdirs();
                        } catch (Exception e) {

                        }
                        byte[] data = null;
                        int bytesRead = 0;
                        int offset = 0;

                        long contentLength = patch.getSize();
                        InputStream raw = patch.getInputStream();
                        in = new BufferedInputStream(raw);
                        long startTime = System.currentTimeMillis();
                        File tempFile = new File(physicalPath);
                        os = new FileOutputStream(tempFile);
                        while (offset < contentLength) {
                            data = new byte[1024 * 1024];
                            bytesRead = in.read(data);
                            os.write(data, 0, bytesRead);
                            if (bytesRead == -1)
                                break;
                            offset += bytesRead;
                        }

                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setName(uploadFileName);
                        fileInfo.setPath(physicalPath);
                        fileInfo.setSize(contentLength);
                        fileUploadRs.setFileInfo(fileInfo);
                        fileUploadRs.setCode(200);
                        fileUploadRs.setMessage("上传成功");

                    }
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
            }
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (Exception e) {
            }
        }
        return fileUploadRs;
    }
    class FileUpload {

        private int code;
        private String message;
        private FileInfo FileInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public FileInfo getFileInfo() {
            return FileInfo;
        }

        public void setFileInfo(FileInfo fileInfo) {
            FileInfo = fileInfo;
        }

        @Override
        public String toString() {
            return "code : " + this.getCode() + " message : " + this.getMessage() + " FileInfo : " + this.getFileInfo();
        }
    }

    class FileInfo {
        private String name;
        private String path;
        private Long size;
        private String mime_type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getMime_type() {
            return mime_type;
        }

        public void setMime_type(String mime_type) {
            this.mime_type = mime_type;
        }

        @Override
        public String toString() {
            return "FileInfo{" +
                    "name='" + name + '\'' +
                    ", path='" + path + '\'' +
                    ", size=" + size +
                    ", mime_type='" + mime_type + '\'' +
                    '}';
        }
    }
}
