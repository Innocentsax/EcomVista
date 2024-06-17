package dev.Innocent.controller;

import dev.Innocent.DTO.request.LoginRequest;
import dev.Innocent.DTO.response.AuthResponse;
import dev.Innocent.config.JwtProvider;
import dev.Innocent.model.TwoFactorOTP;
import dev.Innocent.model.User;
import dev.Innocent.repository.UserRepository;
import dev.Innocent.service.Impl.CustomUserDetailsImpl;
import dev.Innocent.service.Impl.EmailServiceImpl;
import dev.Innocent.service.TwoFactorOtpService;
import dev.Innocent.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsImpl customService;
    private final TwoFactorOtpService twoFactorOtpService;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist != null){
            throw new Exception("Email already exist with another account");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        // Generate Token
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setStatus(true);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req){
        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(username, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        User authUser = userRepository.findByEmail(username);

        if(req.getTwoFactorAuth().isEnable()){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Two factor authentication enabled");
            authResponse.setTwoFactorAuthEnabled(true);
            String otp = OtpUtils.generateOtp();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authUser.getId());
            if(oldTwoFactorOTP != null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }
            TwoFactorOTP twoFactorOTP = twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);
            emailService.sendVerificationOtpEmail(username, otp);
            authResponse.setSession(twoFactorOTP.getId());
            return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login success");
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySignOtp(@PathVariable String otp, @RequestParam String id){
        TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);
        if(twoFactorOTP == null){
            throw new BadCredentialsException("Invalid session id");
        }
        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(twoFactorOTP.getJwt());
            authResponse.setMessage("Two factor authentication success");
            authResponse.setTwoFactorAuthEnabled(true);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        throw new BadCredentialsException("Invalid OTP");
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customService.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}
