package com.freud.opc.utgard.cases;

import java.util.concurrent.Executors;

import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;

import com.freud.opc.utgard.BaseConfiguration;

/**
 * ͬ��Access����ȡĳ����ı仯ֵ
 * 
 * @author Freud
 * 
 */
public class OPCTest4 {

	/** ���ʱ�� */
	private static final int PERIOD = 100;

	/** ����ʱ�� */
	private static final int SLEEP = 2000;

	public static void main(String[] args) throws Exception {

		Server server = new Server(
				BaseConfiguration.getCLSIDConnectionInfomation(),
				Executors.newSingleThreadScheduledExecutor());

		server.connect();

		/**
		 * ����100��λΪ���룬Ϊÿ�δ�OPC��ȡˢ�µļ��ʱ��
		 */
		AccessBase access = new SyncAccess(server, PERIOD);

		/**
		 * ��ʱÿ�����ʱ���ȡһ��ֵ
		 */
		access.addItem("Random.Real5", new DataCallback() {
			private int i;

			public void changed(Item item, ItemState itemstate) {
				System.out.println("[" + (++i) + "],ItemName:[" + item.getId()
						+ "],value:" + itemstate.getValue());
			}
		});

		/** ��ʼ���� */
		access.bind();

		/** ��ǰ�߳�����ʱ�䵥λ������ */
		Thread.sleep(SLEEP);
		/** ���� ���� */
		access.unbind();

		server.dispose();
	}
}
