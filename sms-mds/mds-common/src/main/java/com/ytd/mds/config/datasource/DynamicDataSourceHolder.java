package com.ytd.mds.config.datasource;

import com.ytd.mds.utils.MdsConstants;

public class DynamicDataSourceHolder {
	
    //使用ThreadLocal记录当前线程的数据源name
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/** 
     * 功能：设置数据源name 
     * @param name 
     */  
	public static void putDataSource(String name) {
		holder.set(name);
	}

	/** 
     * 功能：获取数据源name 
     * @return 
     */ 
	public static String getDataSouce() {
		return holder.get();
	}
	
	/** 
     * 功能：设置主机模式
     */  
    public static void putMasterDataSource(){  
        putDataSource(MdsConstants.MASTER_DATASOURCE);
    }  
      
    /** 
     * 功能：设置从机模式
     */  
    public static void putSlaveDataSource(){  
        putDataSource(MdsConstants.SLAVE_DATASOURCE_CODE);
    }  
}
