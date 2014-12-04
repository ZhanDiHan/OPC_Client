package com.freud.dcom.utgard.cases;

import static com.freud.opc.utgard.BaseConfiguration.CONFIG_CLSID;
import static com.freud.opc.utgard.BaseConfiguration.CONFIG_DOMAIN;
import static com.freud.opc.utgard.BaseConfiguration.CONFIG_HOST;
import static com.freud.opc.utgard.BaseConfiguration.CONFIG_PASSWORD;
import static com.freud.opc.utgard.BaseConfiguration.CONFIG_USERNAME;
import static com.freud.opc.utgard.BaseConfiguration.getEntryValue;

import java.net.UnknownHostException;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIClsid;
import org.jinterop.dcom.core.JIComServer;
import org.jinterop.dcom.core.JISession;
import org.openscada.opc.dcom.da.OPCENUMSCOPE;
import org.openscada.opc.dcom.da.impl.OPCGroupStateMgt;
import org.openscada.opc.dcom.da.impl.OPCServer;

/**
 * ͨ��Scope����OPC�����µ�����Group��Ϣ
 * 
 * @author Freud
 * 
 */
public class DCOMTest3 {

	public static void main(String[] args) throws Exception {
		JISystem.setAutoRegisteration(true);

		/**
		 * Session��ȡ
		 */
		JISession _session = JISession.createSession(
				getEntryValue(CONFIG_DOMAIN), getEntryValue(CONFIG_USERNAME),
				getEntryValue(CONFIG_PASSWORD));

		final JIComServer comServer = new JIComServer(
				JIClsid.valueOf(getEntryValue(CONFIG_CLSID)),
				getEntryValue(CONFIG_HOST), _session);

		final IJIComObject serverObject = comServer.createInstance();

		OPCServer server = new OPCServer(serverObject);

		/**
		 * ���һ��Group����Ϣ
		 */
		OPCGroupStateMgt group = server.addGroup("test", true, 100, 1234, 60,
				0.0f, 1033);

		enumerateGroups(server, OPCENUMSCOPE.OPC_ENUM_PUBLIC);
		enumerateGroups(server, OPCENUMSCOPE.OPC_ENUM_PRIVATE);
		enumerateGroups(server, OPCENUMSCOPE.OPC_ENUM_ALL);

		// clean up
		server.removeGroup(group, true);
	}

	/**
	 * ͨ��Scope���Ҳ�����Groups����Ϣ
	 * 
	 * @param server
	 * @param scope
	 * @throws IllegalArgumentException
	 * @throws UnknownHostException
	 * @throws JIException
	 */
	public static void enumerateGroups(final OPCServer server,
			final OPCENUMSCOPE scope) throws IllegalArgumentException,
			UnknownHostException, JIException {
		System.out.println("Enum Groups: " + scope.toString());

		for (final String group : server.getGroups(scope).asCollection()) {
			System.out.println("Group: " + group);
		}
	}

}
