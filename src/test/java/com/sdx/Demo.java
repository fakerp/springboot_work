package com.sdx;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.STSAssumeRoleSessionCredentialsProvider;
import com.aliyuncs.auth.EnvironmentVariableCredentialsProvider;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        // 授权STSAssumeRole访问的Region。以华东1（杭州）为例，其它Region请根据实际情况填写。
        String region = "cn-hangzhou";
        // 填写RAM用户的访问密钥（AccessKey ID和AccessKey Secret）。
        String accessKeyId = "LTAI5tAULtLChU7PwJyMFg1U";
        String accessKeySecret = "IBLiV7XdaD6AgOxAnvveAQtOzEp7xg";
        // 填写RAM角色的ARN信息，即需要扮演的角色ID。
        String roleArn = "acs:ram::1803191474721426:role/myroler";

        // 使用代码嵌入的RAM用户的访问密和RAM角色的RamRoleArn配置访问凭证。
        STSAssumeRoleSessionCredentialsProvider credentialsProvider = CredentialsProviderFactory
                .newSTSAssumeRoleSessionCredentialsProvider(
                        region,
                        accessKeyId,
                        accessKeySecret,
                        roleArn
                );
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "hdcat";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "12.jpg";
        String filename = "D:\\java\\sulian.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
//            String content = "Hello OSS";
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
            ossClient.putObject(bucketName, objectName, bufferedInputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}