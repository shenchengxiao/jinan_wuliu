package com.manager.response;

import java.io.Serializable;

/**
 * Created by shencx on 2017/4/14.
 */
public class ClientUpgradeResponse implements Serializable{

    private Integer id;

    private String name;

    private String version;

    private String packageUrl;

    private String upgradeDesc;

    private Integer platformType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getUpgradeDesc() {
        return upgradeDesc;
    }

    public void setUpgradeDesc(String upgradeDesc) {
        this.upgradeDesc = upgradeDesc;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClientUpgradeResponse{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", packageUrl='").append(packageUrl).append('\'');
        sb.append(", upgradeDesc='").append(upgradeDesc).append('\'');
        sb.append(", platformType=").append(platformType);
        sb.append('}');
        return sb.toString();
    }
}
