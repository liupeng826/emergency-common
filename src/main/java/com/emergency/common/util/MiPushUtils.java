package com.emergency.common.util;


import com.emergency.common.dto.PushBean;
import com.xiaomi.xmpush.server.*;

import java.util.ArrayList;
import java.util.List;

/**
 * http://dev.xiaomi.com/docs/push/push_server/
 * http://dev.xiaomi.com/docs/push/push_quick/
 * 小米推送工具类
 * Created by Administrator on 2016/3/26.
 */
public class MiPushUtils {

	//ios密钥
	private final static String IOS_APP_SECRET=null;
	//安卓密钥
	private final static String ANDROID_APP_SECRET=null;
	//安卓包名
	private final static String ANDROID_PACKAGE_NAME=null;

	private final static int PASS_THROUGH_MESSAGE = 0;//1表示透传消息
	private final static int NOTIFICATION_MESSAGE = 0;//0表示通知栏消息
	private final static int PASS_THROUGH = NOTIFICATION_MESSAGE;//1表示透传消息，0表示通知栏消息

	private final static int DEFAULT_ALL = -1;
	private final static int DEFAULT_SOUND = 1; // 使用默认提示音提
	private final static int DEFAULT_VIBRATE = 2; // 使用默认震动提示
	private final static int DEFAULT_LIGHTS = 4; // 使用默认led灯光提示
	private final static int NOTIFY_TYPE = DEFAULT_ALL;


	public final static int TYPE_ANDROID = 0;
	public final static int TYPE_IOS = 1;


	public static void main(String agrs[]) {
		MiPushUtils miPushUtils = new MiPushUtils();
		try {
			Result result = miPushUtils.sendMessageToAlias("test", "eeee", "1001306", TYPE_ANDROID,0);
			miPushUtils.sendMessageToAlias("test", "eeee", "1001306", TYPE_ANDROID,0);
			miPushUtils.sendMessageToAlias("test", "eeee", "1001306", TYPE_ANDROID,0);
			miPushUtils.sendMessageToAlias("test", "eeee", "1001306", TYPE_ANDROID,0);
			System.out.print("result is " + result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用小米推送
	 */
	public static void reStartPush(int deviceType,int type) {
		if (type == 0) {
			//测试环境
			//测试环境只提供对IOS支持，不支持Android
			Constants.useSandbox();
			if (deviceType == TYPE_ANDROID) {
				Constants.useOfficial();
			}
		} else {
			//正式环境
			Constants.useOfficial();
		}
	}


	/**
	 * 根据regid发送一条短信
	 *
	 * @param title
	 * @param content
	 * @param regId
	 * @param deviceType
	 * @throws Exception
	 */
	public static Result sendMessage(String title, String content, String regId, int deviceType,int type) throws Exception {
		reStartPush(deviceType,type);
		Sender sender = null;
		if (deviceType == TYPE_ANDROID) {
			sender = new Sender(ANDROID_APP_SECRET); //需要根据appSecert来发送
		} else if (deviceType == TYPE_IOS) {
			sender = new Sender(IOS_APP_SECRET); //需要根据appSecert来发送
		}
		Message message = buildMessage(title, content, deviceType);
		Result result = sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，不重试。
		return result;
	}

	/**
	 * 根据alias发送一条短信
	 *
	 * @param title
	 * @param content
	 * @param userMobile
	 * @param deviceType
	 * @param type 环境变量（0：测试环境  1：正式环境）
	 * @throws Exception
	 */
	public static Result sendMessageToAlias(String title, String content, String userMobile, int deviceType,int type) throws Exception {
		reStartPush(deviceType,type);
		Sender sender = null;
		if (deviceType == TYPE_ANDROID) {
			sender = new Sender(ANDROID_APP_SECRET); //需要根据appSecert来发送
		} else if (deviceType == TYPE_IOS) {
			sender = new Sender(IOS_APP_SECRET); //需要根据appSecert来发送
		}
		Result result = sender.sendToAlias(buildMessage(title, content, deviceType), userMobile, 0); //根据alias，发送消息到指定设备上，不重试。
		return result;
	}

	/**
	 * 给一个用户发送一群短信
	 *
	 * @param pushBeans
	 * @param userMobile
	 * @param deviceType
	 * @throws Exception
	 */
	public static Result sendMessages(List<PushBean> pushBeans, String userMobile, int deviceType,int type) throws Exception {
		reStartPush(deviceType,type);
		Sender sender = null;
		if (deviceType == TYPE_ANDROID) {
			sender = new Sender(ANDROID_APP_SECRET); //需要根据appSecert来发送
		} else if (deviceType == TYPE_IOS) {
			sender = new Sender(IOS_APP_SECRET); //需要根据appSecert来发送
		}
		Result result = sender.send(buildMessages(pushBeans, userMobile, deviceType), 0); //根据alias，发送消息到指定设备上，不重试。
		return result;
	}

	/**
	 * 给一群用户发送一条短信
	 *
	 * @param title
	 * @param content
	 * @param userMobiles
	 * @param deviceType
	 * @throws Exception
	 */
	public static Result sendMessageToAliases(String title, String content, List<String> userMobiles, int deviceType,int type) throws Exception {
		reStartPush(deviceType,type);
		Sender sender = null;
		if (deviceType == TYPE_ANDROID) {
			sender = new Sender(ANDROID_APP_SECRET); //需要根据appSecert来发送
		} else if (deviceType == TYPE_IOS) {
			sender = new Sender(IOS_APP_SECRET); //需要根据appSecert来发送
		}
		Result result = sender.sendToAlias(buildMessage(title, content, deviceType), userMobiles, 0);//根据aliasList，发送消息到指定设备上，不重试。
		return result;
	}


	/**
	 * 发送广播
	 * 根据topic，发送消息到指定一组设备上
	 *
	 * @param title
	 * @param content
	 * @param topic
	 * @param deviceType
	 * @throws Exception
	 */
	public static Result sendBroadcast(String title, String content, String topic, int deviceType,int type) throws Exception {
		reStartPush(deviceType,type);
		Sender sender = null;
		if (deviceType == TYPE_ANDROID) {
			sender = new Sender(ANDROID_APP_SECRET); //需要根据appSecert来发送
		} else if (deviceType == TYPE_IOS) {
			sender = new Sender(IOS_APP_SECRET); //需要根据appSecert来发送
		}
		Result broadcast = sender.broadcast(buildMessage(title, content, deviceType), topic, 0);//根据topic，发送消息到指定一组设备上，不重试。
		return broadcast;
	}


	/**
	 * TargetedMessage封装了MiPush推送服务系统中的消息Message对象，和该Message对象所要发送到的目标
	 *
	 * @param pushBeans
	 * @param userMobile
	 * @param deviceType
	 * @return
	 * @throws Exception
	 */
	public static List<TargetedMessage> buildMessages(List<PushBean> pushBeans, String userMobile, int deviceType) throws Exception {
		List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
		for (PushBean pushBean : pushBeans) {
			TargetedMessage message1 = new TargetedMessage();
			message1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, userMobile);
			message1.setMessage(buildMessage(pushBean.getTitle(), pushBean.getContent(), deviceType));
			messages.add(message1);
		}
		return messages;
	}

	/**
	 * 构建发送信息
	 *
	 * @param title
	 * @param content
	 * @param deviceType
	 * @return
	 */
	public static Message buildMessage(String title,
								 String content,
								 int deviceType) throws Exception {
		Message message = null;
		if (deviceType == TYPE_ANDROID) {
			message = buildMessage2Android(title, content);
		} else if (deviceType == TYPE_IOS) {
			message = buildMessage2IOS(content);
		}
		return message;
	}

	/**
	 * 构建android推送信息
	 *
	 * @param title
	 * @param content
	 * @return
	 */
	public static Message buildMessage2Android(String title, String content) throws Exception {
		Message message = new Message.Builder()
				.title(title)
				.description(content).payload(content)
				.restrictedPackageName(ANDROID_PACKAGE_NAME)//设置包名
				.passThrough(PASS_THROUGH)  //消息使用透传方式
				.notifyType(NOTIFY_TYPE) // 使用默认提示音提示
				.enableFlowControl(true)//控制消息是否需要进行平缓发送
				.build();
		return message;
	}

	/**
	 * 构建ios推送信息
	 *
	 * @param content
	 * @return
	 */
	public static Message buildMessage2IOS(String content) throws Exception {
		Message message = new Message.IOSBuilder()
				.description(content)
				.badge(1) // 数字角标
				.build();
		return message;
	}

}
