package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //MVC와 템플릿엔진
    //템플릿엔진을 모델, 뷰, 컨트롤러 방식으로 쪼개서
    //View 보여지는 화면, Controller와 Model 비즈니스 로직과 내부적인 처리
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API
    //객체 반환
    @GetMapping("hello-string")
    @ResponseBody //http 통신프로토콜 body 부분에 이 데이터를 직접 넣겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    //브라우저에서 템플릿엔진과 똑같이 보이지만, 페이지 소스를 보면 "hello " + 입력한 내용만 있음.
    //이렇게 쓰는 경우는 별로 없음.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //브라우저에서 보이는 내용 : {"name":"입력한 내용"}
        //JSON 형식 - 키&값 쌍
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
