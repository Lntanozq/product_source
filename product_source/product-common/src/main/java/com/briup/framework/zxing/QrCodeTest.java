package com.briup.framework.zxing;

public class QrCodeTest {
	public static void main(String[] args) throws Exception {
		// 存放在二维码中的内容
		String text = "http://192.168.0.106:8888/swagger-ui.html";
		// 生成的二维码的路径及名称
		String destPath = "E:/项目/00农产品溯源/slapianzi.jpg";
		//生成二维码
		QRCodeUtil.encode(text, destPath);
		// 解析二维码
		String str = QRCodeUtil.decode(destPath);
		// 打印出解析出的内容
		System.out.println(str);
	}
}
