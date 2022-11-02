package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    //========================= text =================================

    // 기본적인 동적 렌더링
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    // unescape  =>  escape란??  타임리프가 기본적으로 제공, < or > 같은 특수문자를 HTML 엔티티(&lt or &gt)로 변경
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    //============================= 변수 ==============================

    @Data
    static class User {
        private String username;
        private int age;
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);      //객체
        model.addAttribute("users", list);      //리스트
        model.addAttribute("userMap", map);     //map
        return "basic/variable";
    }
    //========================== 기본 객체들 =============================

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    //===================== 유틸리티 객체 ===============================
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    //============================= URL 링크 ===============================
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    //================================ 문자 리터럴 (고정된 문자값) ======================================
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    //================================== 연산 ===================================================
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    //===================================== 속성 값 설정 ===============================================
    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    //======================================== 반복 ===========================================
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }
    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    //=============================== 조건 =============================================
    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    //===================================== 주석 ================================
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    //=================================== 블록 ======================================
    // 렌더링 시 자동으로 제거되는 타임리프 자체 태그!!
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    //========================== 자바스크립트 인라인 =============================
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }
}
