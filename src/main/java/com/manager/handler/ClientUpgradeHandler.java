package com.manager.handler;

import com.manager.enums.PlatformTypeEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.ClientUpgradeInfo;
import com.manager.request.clientupgrade.ClientUpgradeRequest;
import com.manager.response.ClientUpgradeResponse;
import com.manager.service.ClientUpgradeService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/14.
 */
@Service
public class ClientUpgradeHandler {

    Logger LOG = LoggerFactory.getLogger(ClientUpgradeHandler.class);

    @Resource
    private ClientUpgradeService upgradeService;

    /**
     * 添加修改
     * @param request
     * @throws YCException
     */
    public void addClientUpgrade(ClientUpgradeRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getClientName(),"名称不能为空");
        Validator.isEmpty(request.getVersion(),"版本号不能为空");

        ClientUpgradeInfo clientUpgradeInfo = new ClientUpgradeInfo();
        clientUpgradeInfo.setId(request.getId());
        clientUpgradeInfo.setName(request.getClientName());
        clientUpgradeInfo.setVersion(request.getVersion());
        clientUpgradeInfo.setPackageUrl(request.getPackageUrl());
        clientUpgradeInfo.setUpgradeDesc(request.getUpgradeDesc());
        clientUpgradeInfo.setPlatformType(PlatformTypeEnum.create(request.getPlatformType()));

        try {
            if (request.getId() == null) {
                upgradeService.addClientUpgradeInfo(clientUpgradeInfo);
            }else {
                upgradeService.updateClientUpgradeInfo(clientUpgradeInfo);
            }
        } catch (DatabaseException e) {
            LOG.error("addClientUpgrade exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取分页列表
     * @param request
     * @return
     * @throws YCException
     */
    public Page<ClientUpgradeInfo> getUpgradeInfoPage(ClientUpgradeRequest request) throws YCException {
        Page<ClientUpgradeInfo> page = null;
        try {
            page = upgradeService.fetchUpgradeList(request);
            return page;
        }  catch (DatabaseException e) {
            LOG.error("getUpgradeInfoPage exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取详情
     * @param id
     * @return
     * @throws YCException
     */
    public ClientUpgradeResponse getUpgradeInfoDetail(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"主键ID不能为空");
        ClientUpgradeInfo clientUpgradeInfo = null;
        try {
            clientUpgradeInfo = upgradeService.fetchUpgradeById(id);
            ClientUpgradeResponse clientUpgradeResponse = new ClientUpgradeResponse();
            clientUpgradeResponse.setId(clientUpgradeInfo.getId());
            clientUpgradeResponse.setName(clientUpgradeInfo.getName());
            clientUpgradeResponse.setPackageUrl(clientUpgradeInfo.getPackageUrl());
            clientUpgradeResponse.setUpgradeDesc(clientUpgradeInfo.getUpgradeDesc());
            clientUpgradeResponse.setVersion(clientUpgradeInfo.getVersion());
            clientUpgradeResponse.setPlatformType(clientUpgradeInfo.getPlatformType().getValue());
            return clientUpgradeResponse;
        } catch (DatabaseException e) {
            LOG.error("getUpgradeInfoDetail exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}
