package org.nwnu.pub.util;

import org.apache.bcel.classfile.Code;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtil {

	 //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIbvnnh0NALpMI";
    static final String accessKeySecret = "6GZ2G0pO0JczzGBoxzTyjtEAywA4IS";
    static final String SIGNNAME_STRING = "师大orgnwnu17";
    static final String TEMPLATECODE_STRING  = "SMS_86260009";

    private static SendSmsResponse sendSms(String mobile, String verCode) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGNNAME_STRING);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TEMPLATECODE_STRING);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + verCode + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    
    public static boolean sendPhoneCode(String mobile, String verCode) throws ClientException{
    	SendSmsResponse response = sendSms(mobile, verCode);
    	//System.out.println("*******smsresponse********"+response.getCode()+"********"+response.getMessage());
    	if(response.getCode().equals("OK")){
    		return true;
    	}
    	return false;
    }
    
	/*public static void main(String[] args) throws ClientException {
		SendSmsResponse response = sendSms();
	    System.out.println("短信接口返回的数据----------------");
	    System.out.println("Code=" + response.getCode());
	    System.out.println("Message=" + response.getMessage());
	    System.out.println("RequestId=" + response.getRequestId());
	    System.out.println("BizId=" + response.getBizId());
	}*/
}
