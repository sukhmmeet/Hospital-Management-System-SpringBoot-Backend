package com.dhaliwal.hospitalManagement.security;

import com.dhaliwal.hospitalManagement.entity.User;
import com.dhaliwal.hospitalManagement.entity.type.AuthProviderType;
import com.dhaliwal.hospitalManagement.repository.UserRepository;
import com.dhaliwal.hospitalManagement.security.dto.LoginRequestDto;
import com.dhaliwal.hospitalManagement.security.dto.LoginResponseDto;
import com.dhaliwal.hospitalManagement.security.dto.SignupRequestDto;
import com.dhaliwal.hospitalManagement.security.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        if(userRepository.findByUsername(loginRequestDto.getUsername()).isEmpty()) {throw new UsernameNotFoundException("Username not found");}
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();

        assert user != null;
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }
    public User signupInternal(SignupRequestDto signupRequestDto, AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (user != null) {
            throw new IllegalStateException("Username is already in use");
        }

        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build());

        if(authProviderType.equals(AuthProviderType.EMAIL)){
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        User user = signupInternal(signupRequestDto, AuthProviderType.EMAIL, null);
        String token = authUtil.generateAccessToken(user);
        return new SignupResponseDto(user.getId(), user.getUsername(), token);
    }

    public ResponseEntity<LoginResponseDto> handleOAuth2Login(OAuth2User oAuth2User, String registrationId) {
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");

        if(email == null){

            email = oAuth2User
                    .getAttribute("login")
                    + "@github.com";
        }

        User emailUser  = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signupInternal(new SignupRequestDto(username, email), providerType, providerId);
        } else if (user != null) {
            if(!email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with the provider "+ emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }
}
