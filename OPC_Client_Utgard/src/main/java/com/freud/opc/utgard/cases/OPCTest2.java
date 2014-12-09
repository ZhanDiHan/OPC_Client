package com.freud.opc.utgard.cases;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.FlatBrowser;
import org.openscada.opc.lib.da.browser.Leaf;

import com.freud.opc.utgard.BaseConfiguration;

/**
 * 
 * According the clsid get the connection and print all the group and item
 * information
 * 
 * @author Freud
 * 
 */
public class OPCTest2 {

	public static void main(String[] args) throws Exception {

		Server server = new Server(
				BaseConfiguration.getCLSIDConnectionInfomation(),
				Executors.newSingleThreadScheduledExecutor());

		server.connect();

		/** ���OPC���������е�Group��Item */
		dumpTree(server.getTreeBrowser().browse(), 0);
		/** ���OPC�����е�Item */
		dumpFlat(server.getFlatBrowser());

		server.disconnect();
	}

	/**
	 * ����Flat�ṹ������Itemֵ
	 * 
	 * @param browser
	 * @throws IllegalArgumentException
	 * @throws UnknownHostException
	 * @throws JIException
	 */
	private static void dumpFlat(final FlatBrowser browser)
			throws IllegalArgumentException, UnknownHostException, JIException {
		for (String name : browser.browse()) {
			System.out.println(name);
		}
	}

	/**
	 * ����Tree�ṹ
	 * 
	 * @param branch
	 * @param level
	 */
	private static void dumpTree(final Branch branch, final int level) {

		for (final Leaf leaf : branch.getLeaves()) {
			dumpLeaf(leaf, level);
		}
		for (final Branch subBranch : branch.getBranches()) {
			dumpBranch(subBranch, level);
			dumpTree(subBranch, level + 1);
		}
	}

	private static String printTab(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	/**
	 * ��ӡItem
	 * 
	 * @param leaf
	 */
	private static void dumpLeaf(final Leaf leaf, final int level) {
		System.out.println(printTab(level) + "Leaf: " + leaf.getName() + ":"
				+ leaf.getItemId());
	}

	/**
	 * ��ӡGroup
	 * 
	 * @param branch
	 */
	private static void dumpBranch(final Branch branch, final int level) {
		System.out.println(printTab(level) + "Branch: " + branch.getName());
	}
}
