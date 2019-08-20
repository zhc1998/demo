package com.jk.common;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100100642565";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuLMl1wFqJkWFNCKc7E10B99y2W7M6cAENrIfSqFTsiIH1nhcTQAJMr5PJLmmXbxlfwjGwl7z1dI0sT55mAXNiZnSovucrrKEyVGkqbVx0TAmHbJ32QZhOrRHJ5j3n/E1J9xdsuYgwkG75U4wacewWSLaQJwr0UoiuDCeEFxL6bestNv7Z5mJwdhxZ+5xTxgPKsbP9G35nBf3zLyiyWVIAaa8fuHZWZlBCFBuFhRvtQrW0OIQ+NEda6tfpCPqJkit9KHzwYJju7g5NNX4ANFijrc9I4dSeySVkStLZeDBNbvly8ztlg49NFvHrktbhPW/cOuMWL35cwqQkcRFj2lKXAgMBAAECggEANsjznUwhrj/CQT7Fizlt+d6C2AFEAM+WJTztg/9apwPRDPbq/y0VDBX2AEPPBsj1TD+C7a7syrAcb6GjiyZNqtfbPfC+OeiU04XlhmjbueDo5mXt0bWUOLsLa1w2gK/Fh8xm29YH1m632UjEOtU2k1PPyMvfEDads1EzZML1PzuqswjZN/RUFIvRs9zIsf+qWAuAYtnHPzyirsJZ69PQ3y0P035RFXWsrg2cRWF+4icb2qoYGDswbCSITr4KN5M65H68svK0lP8FQr4AIrgFmCW2FMd4iGnz8OVCIeWlfadTE/lXTuojmyZ6Gmx0HObgocOcwutWOuZ7DuvXaBBCkQKBgQDgtYSpDfejtaB8dl9aMcPc8KuFgnv1hAih+w8f1uWcF903gpCq9mT8/46m5duxsRlhr0H9g/fZ/U4aA9+fVKNGW5hhQBxVybcRBF4GmJUzWSCx9Scm6/D1kDL9NnoTDLF3RLIilxQgdy6FXHi61kh1yfRAy32lrfI7mPF0uMKO0wKBgQDGbdEhZ4UvbnXfeh6ZOmtPN1cr14Nq3tGFp3CsaO36EzIL3KnDCEtp6j7J8/aOk51E/tqGZorn0qJ5QNt0ORAlicLDBcoeCrfX1SIK4X9M1JTvfu1bBBtRcqpjRUI57I5O5mTr5aPu1PzmQGKUI9tM+JDqlkx+xKmJyO9tIWw6rQKBgQCeuRjfoPIPPTgDMkngF87VvVoqkCSnI4BFimUAhHbjbyffEebGHAzxeEJ3OV2ZghL/oR/nxtPHstj8H0hR7yL1i7Vy3Connrgk2QThe93Hy2kAyr3BDF/D1PtvSI+p7QldybRaIoUg+Y5uvQa6UqoUKXbSXep+oQO9fSbw36Dt6QKBgH1peQ0Azaety+gj2eUEjEAz/O9jtda833X23vrJEufEbBQdMlpnCqr1dxvTCpkQaQzrepgJhW+BrFiwikNJsY/YIvV52kXkhk9ukKaJ4RhhH4TDFyc4UD/JFjHJ21Ka1VK+wThrGpJfoTuxo9x3USqzy3vI5K4WhATmr5Ss1Ce9AoGAKH4ANvU2v6CrRCjuwgUQhKezQg6zT1Spc8/zpzoQcLtlxrupaNbqqBxgtbEyxWhk/NE56gER9FDilxn8ZNb7b9EZDKT7F9EzhBnoKEdGEpdOFn63JP7myFuEHtFDN7XttcrUyJoLIiuBvfzT5g+Yj1zPncAUXv6nRGmY6oA+xis=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApt5ARCxLqsZbBRmfRvyQLGfP/XzkraqVlWa2DMCDTEQCgL1AtO/1Q498pKYNxJPzl3eAEPP/bbwKc05fiiBiBWFwhSu/ElK+/g+F/RStu7ZTI2oetvdogj/WRvhHQ9L88EE7LIWuVO1Py/MGuIv+UC3SuKOkzF4pq0iHlGpofZPK2cxgaFiEOL4Eym6hPPYXHGMKzcFxUgoYK2m/Z97TwFrNIm/fj8rM/Zimn2QBUWRwcKulRxnT0TEFu9iV4sRzlx8TXueKYfXrLm+4kl9ntCPF8cMgDBuCEZbkt1qiUKE6uw74fwuX4WW1EqVjwNXTnBjupk+h3AripQ1mNkgr3QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
