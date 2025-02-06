package com.example.Back_End.Services.Impl;

import com.example.Back_End.DTO.LoginDTO;
import com.example.Back_End.DTO.VehicleOwnerDTO;
import com.example.Back_End.Entity.User;
import com.example.Back_End.Entity.VehicleOwner;
import com.example.Back_End.Repository.UserRepository;
import com.example.Back_End.Repository.VehicleOwnerRepository;
import com.example.Back_End.Response.LoginResponse;
import com.example.Back_End.Services.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VehicleOwnerServiceIMPL implements VehicleOwnerService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String addVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO) {
        // Create a VehicleOwner instance using the DTO values
        VehicleOwner vehicleOwner = new VehicleOwner();

        vehicleOwner.setOwnerName(vehicleOwnerDTO.getOwnerName());
        vehicleOwner.setEmail(vehicleOwnerDTO.getOwnerEmail());
        vehicleOwner.setPassword(passwordEncoder.encode(vehicleOwnerDTO.getOwnerPassword()));
        vehicleOwner.setOwnerPhone(vehicleOwnerDTO.getOwnerPhone());
        vehicleOwner.setVehicles(null);

        // Create a VehicleOwner instance using the DTO values
//        VehicleOwner vehicleOwner = new VehicleOwner(
//                null, // Primary key (ID) is auto-generated
//                vehicleOwnerDTO.getOwnerName(),
//                vehicleOwnerDTO.getOwnerEmail(),
//                passwordEncoder.encode(vehicleOwnerDTO.getOwnerPassword()),
//                vehicleOwnerDTO.getOwnerPhone(),
//                null // The list of vehicles is initially null
//        );

        // Save the VehicleOwner to the repository
        vehicleOwnerRepository.save(vehicleOwner);

        return "{\"message\" : \"Vehicle Owner added successfully!\"}";
    }

    @Override
    public LoginResponse loginVehicleOwner(LoginDTO loginDTO) {
        // Find the VehicleOwner by email
        Optional<VehicleOwner> vehicleOwnerOpt = vehicleOwnerRepository.findByEmail(loginDTO.getEmail());

        if (vehicleOwnerOpt.isPresent()) {

            VehicleOwner vehicleOwner = vehicleOwnerOpt.get();
            int ownerId = vehicleOwner.getOwnerID();
            String password = loginDTO.getPassword();
            String encodedPassword = vehicleOwner.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<VehicleOwner> newowner = vehicleOwnerRepository.findOneByEmailAndPassword(
                        loginDTO.getEmail(), encodedPassword);
                if (newowner.isPresent()) {
                    return new LoginResponse(ownerId,"Login Success", true);
                } else {
                    return new LoginResponse(ownerId,"Login Failed", false);
                }
            } else {
                return new LoginResponse(ownerId,"Password does not match", false);
            }
        } else {
            return new LoginResponse(0,"Owner does not exist", false);
        }
    }

    @Override
    public int getOwnerID(int userID) {
        Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            Optional<VehicleOwner> vehicleOwner = vehicleOwnerRepository.findOneByUser(user.get());
            if(vehicleOwner.isPresent()) {
                return vehicleOwner.get().getOwnerID();
            }
            else{
                throw new RuntimeException("UserID does not exist in vehicle_owner table");
            }
        } else{
            throw new RuntimeException("User does not exist");
        }
    }
}
