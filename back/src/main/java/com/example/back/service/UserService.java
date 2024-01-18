package com.example.back.service;

import com.example.back.Exception.AppException;
import com.example.back.Exception.ErrorCode;
import com.example.back.Respository.UserRepository;
import com.example.back.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.back.Config.SpringSecurity.utils.JwtUtil.createJwt;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password) {
        // userNAme 중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATE, userName + "은 이미 존재하는 이름입니다.");
                });
        //저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);
        return "SUCCESS";
    }


    @Value("${jwt.token.secret}")
    private String key;

    private int expiredMs = 1000 * 60 * 60 * 24 * 5;

    public String login(String userName, String password) {

        //userName 없음
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));


        //password 틀림
        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드 잘못 입력");
        }

        //사용자 권한
        List<String> roles = new ArrayList<>();
        //roles.add("USER");
        roles.add("ADMIN");

        return createJwt(selectedUser.getUserName(), expiredMs, key, roles);


    }
}
