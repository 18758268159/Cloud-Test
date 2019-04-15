package com.ethan.web.webmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebMvcApplicationTests {

    @Test
    public void contextLoads() {



//        String jsName = "C:\\Users\\18758\\Downloads\\StudyCode\\souhuLogin.js";
//        FileReader fileReader = null;
//        try {
//            fileReader = new FileReader(jsName);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        String jsTxt = "C:\\Users\\18758\\Downloads\\StudyCode\\JS.txt";


        FileReader fileReader1 = null;
        BufferedReader bufr = null;
        String line = null;
        try {
            fileReader1 = new FileReader(jsTxt);
            bufr = new BufferedReader(fileReader1);
            //BufferedReader提供了按行读取文本文件的方法readLine()
            //readLine()返回行有效数据，不包含换行符，未读取到数据返回null
            line = bufr.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("异常：" + e.toString());
        }

        String js = "function getjs(str) { return eval(str); }";
        //执行指定脚本
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval(js);
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                System.out.println(in.invokeFunction("getjs" , line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
