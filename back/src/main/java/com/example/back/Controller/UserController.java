package com.example.back.Controller;

import com.example.back.domain.dto.UserJoinRequest;
import com.example.back.domain.dto.UserLoginRequest;
import com.example.back.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
        userService.join(dto.getUserName(), dto.getPassword());
        return ResponseEntity.ok().body("회원 가입 성공");
    }


    //login
    //username
    //password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {

        String username = request.getUserName();
        String password = request.getPassword();


        return ResponseEntity.ok().body(userService.login(username, password));
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(Authentication authentication) {
        //카테고리를 저장할 때 토큰에 있는 user 정보를 꺼내서 사용자_id를 입력받지 않아도 token에 들어 있는 것으로 사용 가능


        return ResponseEntity.ok().body(authentication.getName() + "카테고리 선택 완료");
    }
}
