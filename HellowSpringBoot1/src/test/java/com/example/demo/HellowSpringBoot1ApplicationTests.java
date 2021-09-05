/*package com.example.demo;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HellowSpringBoot1ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	 @Value("${web.upload-path}")
	    private String path;

	    *//** 文件上传测试 *//*
	    @Test
	    public void uploadTest() throws Exception {
	        File f = new File("D:/13.png");
	        FileCopyUtils.copy(f, new File(path+"/1223.png"));
	    }
	    
	    
	    @Test
	    public void listFilesTest() {
	        File file = new File(path);
	        for(File f : file.listFiles()) {
	            System.out.println("fileName : "+f.getName());
	        }
	    }

}
*/