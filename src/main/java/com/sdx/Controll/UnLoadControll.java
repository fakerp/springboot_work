package com.sdx.Controll;

import com.aliyuncs.exceptions.ClientException;
import com.sdx.my_class.Result;
import com.sdx.my_function.Oss_use;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UnLoadControll {
    @Autowired
    Oss_use oss;
//    @PostMapping("/upload")
////    大型二进制文件用MultipartFile来进行接收
//    public void upload(String username , String age , MultipartFile image) throws IOException {
////        用getOriginalFilename来获得图片的全程名xxx.文件类型
//        String File_name = image.getOriginalFilename();
////        用UUID生成唯一标识符，防止文件之间产生覆盖
//        UUID uuid = UUID.randomUUID();
////        找到.的位置
//        int i = File_name.indexOf('.');
////        将uuid和.文件类型结合，生成唯一标识的路径地址
//        String File_last = File_name.substring(i);
//        String File_path = uuid+File_last;
//        image.transferTo(new File("D:\\java\\"+File_path));
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws ClientException, IOException {
        log.info("输出的信息为{}",image.getOriginalFilename());
      return Result.success(oss.get_String(image));
    }

}
