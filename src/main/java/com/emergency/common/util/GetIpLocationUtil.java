package com.emergency.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class GetIpLocationUtil {

    /**
     * 百度
     *
     * @param ip
     * @return
     */
    public static String[] getIPXY(String ip) {

        String ak = "GdE1QMleCBIVCQwcaDqDiy4FpcTdaqHf";
        if (null == ip) {
            ip = "";
        }
        try {

//            URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + ak
//                    + "&ip=" + ip + "&coor=bd09ll");
            URL url = new URL("http://api.map.baidu.com/highacciploc/v1?qcip="+ip+"&qterm=pc&extensions=1&ak="+ak+"&coord=bd09ll");
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuffer sb = new StringBuffer();
            String str;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);


            str = sb.toString();
//            convertUnicode(str);
            if (null == str || str.isEmpty()) {
                return null;
            }


            // 获取坐标位子
            int index = str.indexOf("point");
            int end = str.indexOf("}}", index);


            if (index == -1 || end == -1) {
                return null;
            }


            str = str.substring(index - 1, end + 1);
            if (null == str || str.isEmpty()) {
                return null;
            }


            String[] ss = str.split(":");
            if (ss.length != 4) {
                return null;
            }


            String x = ss[2].split(",")[0];
            String y = ss[3];


            x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
            y = x.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));


            return new String[] { x, y };


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     *
     * @param content   淘宝
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
//        String urlStr="http://whois.pconline.com.cn";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = convertUnicode(returnStr);
            System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
            }
            return returnStr;
        }
        return null;
    }

    //太平洋
    public static String getAddressByIP(String strIP)
    {
        try
        {
//            URL url = new URL( "http://fw.qq.com/ipaddress?ip=" + strIP);
            //太平洋
            URL url = new URL( "http://whois.pconline.com.cn/?ip=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            reader.close();
            strIP = result.substring(result.indexOf( "位置：" ));
            strIP = strIP.substring(strIP.indexOf("：") + 1);
            strIP = strIP.substring(0,strIP.indexOf("</p>")).trim();
            String province=null;
            String city=null;
            String region=null;
            if(strIP.indexOf("省")>0){
                province = strIP.substring(0, strIP.indexOf("省")+1);
                city = strIP.substring(strIP.indexOf("省") + 1, strIP.indexOf("市")+1);
            }else {
                province=city=strIP.substring(0,strIP.indexOf("市")+1);
            }
            region=strIP.substring(strIP.indexOf("市") + 1, strIP.indexOf("区")+1);
            String isp =strIP.substring(strIP.indexOf("区") + 1);

        }
        catch( IOException e)
        {
            return "读取失败";
        }
        return null;
    }

    /**
     * @param urlStr
     *            请求的地址
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }




    public static void main(String[] args) throws Exception {

//		Map<String, Object> map = new HashMap<String, Object>();
//		EaseMobUtil.getUser(map);
//		String token = EaseMobUtil.token(map);
        //Map<String, Object> rsp = EaseMobUtil.addUser("test5", "password5", "nickname5", "YWMtv-IMzqxUEeSMBfFCdjGeKQAAAUyJBuP9k4w3YIsyX5iIZuRZRZtByAeXyMA", map);
        //Map<String, Object> rsp = EaseMobUtil.deleteUser("test3", token, map);
        //Map<String, Object> rsp = EaseMobUtil.addFriend("test1", "test2", token, map);
        //Map<String, Object> rsp = EaseMobUtil.deleteFriend("test1", "test2", token, map);
        //List<String> memberList = new ArrayList<String>();
        //memberList.add("test2");
        //Map<String, Object> rsp = EaseMobUtil.createGroup("test-group", "test-desc", "test1", memberList, token, map);
        //Map<String, Object> rsp = EaseMobUtil.deleteGroup("142342053787944", token, map);
        //Map<String, Object> rsp = EaseMobUtil.groupAddMember("142342483986528", "test3" , token, map);
        //Map<String, Object> rsp = EaseMobUtil.groupDeleteMember("142342483986528", "test2" , token, map);

//		if(rsp == null) {
//			System.out.println(map.toString());
//		}else {
//			System.out.println(rsp.toString());
//		}
//		String str="12|3|4";
//		String[] m=str.split("\\|");
//		str=str.substring(0,str.length()-2);
//		System.out.println(m);
//        Boolean b=isChinese("你好 ");
//        String[] xy= getIPXY("220.181.38.113");
//        System.out.println(xy);


        String ip = "180.76.169.196";
        String address = "";
        try {
            address = getAddresses("ip="+ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(address);
//        getAddressByIP(ip);

    }
}
