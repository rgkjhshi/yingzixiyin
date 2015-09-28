package com.gson.bean;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 预订单vo，微信支付统一下单的一个接口
 * @author lkzlee
 *
 */
public class PreOrder implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;
	//微信支付分配的商户号
	private String mch_id;
	//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String device_info;
	//随机字符串，不长于32位。推荐随机数生成算法
	private String nonce_str;
	//签名，详见签名生成算法
	private String sign;
	//商品或支付单简要描述
	private String body;
	//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	private String out_trade_no;
	//订单总金额，只能为整数，详见支付金额
	private int total_fee;
	//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	private String spbill_create_ip;
	//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	//接收微信支付异步通知回调地址
	private String notify_url;
	//取值如下：JSAPI，NATIVE，APP，WAP,详细说明见参数规定
	private String trade_type;
	//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
	//下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
	//企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换。
	private String openid;
	
	
	public PreOrder(String appid, String mch_id, String device_info,
			String nonce_str, String sign, String body, String out_trade_no,
			int total_fee, String spbill_create_ip, String notify_url,
			String trade_type, String openid) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.openid = openid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String serializeToXml() throws IllegalArgumentException, IllegalAccessException{
		StringBuffer sb=new StringBuffer();
		sb.append("<xml>");
		Field fs[]=PreOrder.class.getDeclaredFields();
		for(int i=0;null!=fs&&i<fs.length;i++){
			if("serialVersionUID".equals(fs[i].getName())){
				continue;
			}
			fs[i].setAccessible(true);
			Object value=fs[i].get(this);
			if(value==null){
				continue;
			}
			sb.append("<"+fs[i].getName()+">");
			sb.append(value);
			sb.append("</"+fs[i].getName()+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
