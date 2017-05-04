package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.exception.YCException;
import com.manager.handler.BannerHandler;
import com.manager.pojo.BannerInfo;
import com.manager.request.BaseQuery;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.response.AppAdvertisementResponse;
import com.manager.response.BannerInfoResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.IdGenerator;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.file.path}")
    private String filePath;


    Logger LOG = LoggerFactory.getLogger(BannerController.class);

    /**
     * 添加banner
     * @param request
     * @param bannerInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editor",method = RequestMethod.POST)
    public APIResponse editor(HttpServletRequest request, BannerInfo bannerInfo){
        APIResponse apiResponse = new APIResponse();
        try {
            bannerHandler.addBanner(bannerInfo);
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
        } catch (Throwable e) {
            LOG.error("list 发生异常",baseQuery);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public APIResponse delete(HttpServletRequest request ,Integer id){
        APIResponse apiResponse = new APIResponse();
        try {
            bannerHandler.deleteBannerInfo(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("delete 发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 启用、禁用
     * @param request
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public APIResponse modify(HttpServletRequest request ,Integer id,Integer status){
        APIResponse apiResponse = new APIResponse();
        try {
            bannerHandler.modifyBannerStatus(id,status);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("modify 发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }


    /**
     * 获取详情信息
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public APIResponse<BannerInfoResponse> detail(HttpServletRequest request, Integer id){
        APIResponse<BannerInfoResponse> apiResponse = new APIResponse<>();
        BannerInfoResponse bannerInfoResponse = null;

        try {
            bannerInfoResponse = bannerHandler.getBannerDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(bannerInfoResponse);
        } catch (YCException e) {
            LOG.error("list 发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取所有广告（为app提供）
     * @param request
     * @param advertInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all_advert",method = RequestMethod.GET)
    public APIResponse<AppAdvertisementResponse> allAdvert(HttpServletRequest request, AdvertInfoRequest advertInfoRequest){
        APIResponse<AppAdvertisementResponse> apiResponse = new APIResponse<>();
        AppAdvertisementResponse appAdvertisementResponse = null;
        try {
            appAdvertisementResponse = bannerHandler.fetchAdvertisement(advertInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(appAdvertisementResponse);
        } catch (YCException e) {
            LOG.error("allAdvert 发生异常",advertInfoRequest);
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

                    //文件上传位置
                    String saveFilePath = filePath;
                    File dirTempFile = new File(saveFilePath);
                    String new_fileName= uuidFileName + "." + fileType;
                    final String physicalPath = saveFilePath +new_fileName;
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
                    fileInfo.setPath(new_fileName);
                    fileInfo.setSize(contentLength);
                    fileUploadRs.setFileInfo(fileInfo);
                    fileUploadRs.setCode(200);
                    fileUploadRs.setMessage("上传成功");


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
