package org.example.department_of_motor_traffic.Controller;


import org.example.department_of_motor_traffic.DTO.VehicleRegistrationDTO;
import org.example.department_of_motor_traffic.Service.MotorTrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicles")
public class MotorTrafficController {

    private final MotorTrafficService service;

    public MotorTrafficController(MotorTrafficService service) {this.service = service;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateVehicle(@RequestParam String licensePlate) {
        boolean isValid = service.validateVehicle(licensePlate);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/addVehicle")
    public VehicleRegistrationDTO addVehicle(@RequestBody VehicleRegistrationDTO vehicleRegistrationDTO) {
        return service.addVehicle(vehicleRegistrationDTO);
    }
}
