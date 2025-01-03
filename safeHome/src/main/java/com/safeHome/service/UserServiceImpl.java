package com.safeHome.service;

import com.safeHome.exception.ResourceNotFoundExp;
import com.safeHome.model.UpdateModel;
import com.safeHome.model.User;
import com.safeHome.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public String sendOtp(String employeeId) throws ResourceNotFoundExp {
        User user = findUserByEmployeeId(employeeId);

        String otp = generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);

        sendOtpToMobile(user.getMobile(), otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(String employeeId, String otp) throws ResourceNotFoundExp {
        User user = findUserByEmployeeId(employeeId);

        if (user.getOtp() != null &&
                user.getOtp().equals(otp) &&
                user.getOtpExpiry() != null &&
                user.getOtpExpiry().isAfter(LocalDateTime.now())) {

            user.setOtp(null);
            user.setOtpExpiry(null);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    private User findUserByEmployeeId(String employeeId) throws ResourceNotFoundExp {
        return userRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExp("User not found with employeeId: " + employeeId));
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }

    private void sendOtpToMobile(String mobile, String otp) {
        System.out.println("Sending OTP: " + otp + " to mobile: " + mobile);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User user(String employeeId) {
        User user = userRepo.findByEmployeeId(employeeId).
                orElseThrow(() -> new ResourceNotFoundExp("User with given Id not found: " + employeeId));
        return user;
    }

    @Override
    public User updateLocation(String employeeId, UpdateModel updateModel) throws ResourceNotFoundExp {
        User existingUser = userRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExp("User with given Id not found: " + employeeId));

        if (updateModel.getLatitude() != null || updateModel.getLatitude() == null) {
            existingUser.setLatitude(updateModel.getLatitude());
        }
        if (updateModel.getLongitude() != null || updateModel.getLongitude() == null) {
            existingUser.setLongitude(updateModel.getLongitude());
        }

        return userRepo.save(existingUser);
    }


}
