package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @desc 获取用户列表信息测试
 */
public class GetClxxList {

	private ExpHttpClient client = null;

	@Before
	public void setUp() throws Exception {
		client = new ExpHttpClient();
	}

	@Test
	public void testDosmsSend() {
		/*
		String methodName = "";
		OnlineRequest req = new OnlineRequest();
		//客户端标识
		String uid = "suncreate";
		req.setUid(uid);
		//apmac
		String apmac = "58-69-6C-4A-DD-2F";	
		if (StringUtils.isNotBlank(apmac)) {
			// 数据库中存的apmac格式需要进行处理
			apmac = apmac.replace('-', ':');
		}
		req.setApmac(apmac);	
		//获取时间戳
		long time = System.currentTimeMillis();  
		String times = Long.toString(time);
		req.setTimestamp(times);
		//MD5加密
		String secret = "sun";
		String authenticator = uid + apmac + times + secret ;

		String authenticatorMD5 = MD5Encrypt.getInstance().encrypt(authenticator);
		authenticatorMD5 = StringUtils.upperCase(authenticatorMD5);
		req.setAuth(authenticatorMD5);	

		// 查询结果
		String result = client.doPost(methodName, req);
		// 控制台打印
		System.out.println(result);
	}

		 */
		String methodName = "clxxList";
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginTime", "2009-10-31 10:47:16");
		map.put("endTime", "2010-8-16 16:14:14");

		// 查询结果
		String result = client.doPost2(methodName, map);
		// 控制台打印
		System.out.println(result);
	}
}