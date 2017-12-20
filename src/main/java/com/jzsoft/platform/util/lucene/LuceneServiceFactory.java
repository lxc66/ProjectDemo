package com.jzsoft.platform.util.lucene;

import com.jzsoft.platform.util.PropertyUtil;
import com.jzsoft.platform.util.lucene.impl.LocalLuceneService;
import com.jzsoft.platform.util.lucene.impl.NetLuceneService;

public class LuceneServiceFactory {

	private static boolean isLocal = true;

	static {
		String type = PropertyUtil.getProperty("/conf/lucene.properties", "lucene.type");
		if (!"0".equals(type)) {
			isLocal = false;
		}
	}

	private static ILuceneService localLuceneService = new LocalLuceneService();

	private static ILuceneService netLuceneService = new NetLuceneService();

	public static ILuceneService getService() {
		return isLocal ? localLuceneService : netLuceneService;
	}
}
