package com.haoyongsys.erp.common.base.push.umeng.ios;

import com.haoyongsys.erp.common.base.push.umeng.IOSNotification;

public class IOSUnicast extends IOSNotification {
	public IOSUnicast(String appkey,String appMasterSecret) throws Exception{
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
