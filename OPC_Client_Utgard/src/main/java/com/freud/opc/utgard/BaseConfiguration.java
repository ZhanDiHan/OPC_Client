package com.freud.opc.utgard;

import java.io.IOException;
import java.util.Properties;

import org.openscada.opc.lib.common.ConnectionInformation;

/**
 * �����ļ�������
 * 
 * @author Freud
 * 
 */
public final class BaseConfiguration {

	private final static ConnectionInformation ci;
	private final static Properties prop;

	public final static String CONFIG_USERNAME = "username";
	public final static String CONFIG_PASSWORD = "password";
	public final static String CONFIG_HOST = "host";
	public final static String CONFIG_DOMAIN = "domain";
	public final static String CONFIG_CLSID = "clsid";
	public final static String CONFIG_PROGID = "progid";

	private final static String CONFIG_FILE_NAME = "config.properties";

	/**
	 * ���������ļ�
	 */
	static {
		ci = new ConnectionInformation();
		prop = new Properties();
		try {
			prop.load(BaseConfiguration.class.getClassLoader()
					.getResourceAsStream(CONFIG_FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͨ�����ֻ�����õ�ֵ
	 * 
	 * @param name
	 * @return
	 */
	public static String getEntryValue(String name) {
		return prop.getProperty(name);
	}

	/**
	 * ��ð���ClsId��������Ϣ
	 * 
	 * @return
	 */
	public static ConnectionInformation getCLSIDConnectionInfomation() {
		ci.setProgId(null);
		getConnectionInfomation();
		ci.setClsid(prop.getProperty(CONFIG_CLSID));
		return ci;
	}

	/**
	 * ��ð���ProgId��������Ϣ
	 * 
	 * @return
	 */
	public static ConnectionInformation getPROGIDConnectionInfomation() {
		ci.setClsid(null);
		getConnectionInfomation();
		ci.setProgId(prop.getProperty(CONFIG_PROGID));
		return ci;
	}

	/**
	 * ��û�����������Ϣ
	 */
	private static void getConnectionInfomation() {
		ci.setHost(prop.getProperty(CONFIG_HOST));
		ci.setDomain(prop.getProperty(CONFIG_DOMAIN));
		ci.setUser(prop.getProperty(CONFIG_USERNAME));
		ci.setPassword(prop.getProperty(CONFIG_PASSWORD));
	}
}
