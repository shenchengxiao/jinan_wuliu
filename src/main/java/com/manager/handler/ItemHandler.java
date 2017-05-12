package com.manager.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.SystemParam;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.mapper.UserMapper;
import com.manager.pojo.Deletehistory;
import com.manager.pojo.Items;
import com.manager.request.item.ItemRequest;
import com.manager.response.ItemResponse;
import com.manager.service.ItemService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

@Service
public class ItemHandler {


    @Resource
    private ItemService itemService;
    @Resource
    private UserMapper userMapper;

    Logger LOG = LoggerFactory.getLogger(ItemHandler.class);

    public Page<ItemResponse> fetchItemList(ItemRequest itemRequest) throws YCException{
        // TODO Auto-generated method stub
        /** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<ItemResponse> page = null;
        try {
            page = itemService.fetchItemList(itemRequest);

            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchItemList exception",itemRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }

    public void deleteItemById(Integer id) throws YCException{
        // TODO Auto-generated method stub
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            itemService.deleteItemById(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    public void deleteItems(String ids) throws YCException{
        // TODO Auto-generated method stub
        /** 参数校验 */
        Validator.isEmpty(ids,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            List<Long> list = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(ids);
            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.parseObject(object.toString());
                String id =jsonObject.get("id").toString();
                list.add(Long.valueOf(id));
            }

            itemService.deleteItems(list);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",ids);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    public void updateItemStatue(Integer id) throws YCException{
        // TODO Auto-generated method stub
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            itemService.updateItemStatue(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    public boolean addItem(Items item) throws YCException{
        /** 参数校验 */
        Validator.isEmpty(item, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(item.getUserPhones(),"用户电话不能为空");
        Validator.isEmpty(item.getContent(),"内容不能为空");

        return this.httpclientPostItem(SystemParam.INTERFACE_URL+"backgroundpublish", item);
    }

    /**
     * 获取清除日志列表
     * @param itemRequest
     * @return
     * @throws YCException
     */
    public Page<ItemResponse> getItemsLogList(ItemRequest itemRequest) throws YCException {
        Page<ItemResponse> page = null;
        try {
            page = itemService.fetchItemBackupList(itemRequest);
            return page;
        }catch (DatabaseException e) {
            LOG.error("getItemsLogList exception",itemRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    //使用http方式调用接口
    private boolean  httpclientPostItem(String url,Items item) throws YCException{
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("typeId", String.valueOf(item.getTypeId())));
        formparams.add(new BasicNameValuePair("content", item.getContent()));
        formparams.add(new BasicNameValuePair("userid", String.valueOf(item.getUserId())));
        formparams.add(new BasicNameValuePair("userPhones", item.getUserPhones()));
        UrlEncodedFormEntity uefEntity;
        net.sf.json.JSONObject jsonResult = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if(response.getStatusLine().getStatusCode() == 200){
                    String str = "";
                    if(entity != null){
                        /**读取服务器返回过来的json字符串数据**/
                        str = EntityUtils.toString(entity);
                        /**把json字符串转换成json对象**/
                        jsonResult = net.sf.json.JSONObject.fromObject(str);
                        if(jsonResult.getString("status") .equals("0")){
                            return true;
                        }
                    }
                }
            } finally {
                response.close();
            }
        }catch (Exception e) {
            LOG.error("调用接口系统异常", e);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;

    }

	public Page<ItemResponse> fetchItemList2(ItemRequest itemRequest) throws YCException{
		/** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<ItemResponse> page = null;
        try {
            page = itemService.fetchItemList2(itemRequest);

            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchItemList2 exception",itemRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
	}

	public void deleteItemsLog(String ids) throws YCException{
		// TODO Auto-generated method stub
		 /** 参数校验 */
        Validator.isEmpty(ids,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            List<Long> list = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(ids);
            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.parseObject(object.toString());
                String id =jsonObject.get("id").toString();
                list.add(Long.valueOf(id));
            }

            itemService.deleteItemsLog(list);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",ids);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}

	public Page<Deletehistory> selectItemCleanNotes(ItemRequest itemRequest) throws YCException{
		// TODO Auto-generated method stub
		Page<Deletehistory> page = null;
        try {
            page = itemService.selectItemCleanNotes(itemRequest);
            return page;
        }catch (DatabaseException e) {
            LOG.error("selectItemCleanNotes exception",itemRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}
}
