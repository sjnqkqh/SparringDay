package com.example.sparringday.service;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.code.ApiExceptionCode;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public User createNewUser(String loginId, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = User.builder().loginId(loginId).encryptedPassword(encryptedPassword).build();

        return userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public User getExistUser(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(Exception::new);
    }

    @Transactional(readOnly = true)
    protected boolean checkUserLoginIdDuplication(String loginId) {
        return userRepository.existsByLoginIdAndIsDeleted(loginId, false);
    }

    @Transactional(readOnly = true)
    public Boolean login(String loginId, String password) {
        User user = userRepository.findUserByLoginIdAndIsDeleted(loginId, false).orElseThrow(() -> new CommonException(ApiExceptionCode.LOGIN_ID_NOT_MATCH_ERROR));
        return passwordEncoder.matches(password, user.getEncryptedPassword());
    }

}
