package com.freud.opc.utgard.cases;

import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;

import com.freud.opc.utgard.BaseConfiguration;

/**
 * �첽д��
 * 
 * @author Freud
 * 
 */
@Deprecated
public class OPCTest8 {

	public static void main(String[] args) throws Exception {

		final Server server = new Server(
				BaseConfiguration.getCLSIDConnectionInfomation(),
				Executors.newSingleThreadScheduledExecutor());

		server.connect();

		/** ����д������ */
		final Float[] integerData = new Float[] { 1202f, 1203f, 1204f };
		final JIArray array = new JIArray(integerData, false);
		final JIVariant value = new JIVariant(array);

		final Item item = server.addGroup().addItem("Square Waves.Real4");

		/** ͬ��д�� */
		item.write(value);
		Thread.sleep(2000);

		/** Dump��item��ֵ */
		dumpItem(item);

		server.dispose();

	}

	private static void dumpItem(Item item) throws JIException {
		System.out.println("[" + (++count) + "],ItemName:[" + item.getId()
				+ "],value:" + item.read(true).getValue());
	}

	private static int count;
}
