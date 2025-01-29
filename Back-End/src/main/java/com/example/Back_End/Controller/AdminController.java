package com.example.Back_End.Controller;

import com.example.Back_End.DTO.AdminDTO;
import com.example.Back_End.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admins") // Define the mapping for admin-related endpoints
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get all admins
    @GetMapping("/get")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no admins found
        }
        return ResponseEntity.ok(admins); // Return 200 with the list of admins
    }

    // Get admin by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Optional<AdminDTO> adminDTO = adminService.getAdminById(id);
        return adminDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    // Create a new admin
    @PostMapping("/create")
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        AdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return ResponseEntity.status(201).body(createdAdmin); // Return 201 if created
    }

    // Update an existing admin
    @PutMapping("/update/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id,@Valid @RequestBody AdminDTO adminDTO) {
        Optional<AdminDTO> updatedAdmin = adminService.updateAdmin(id, adminDTO);
        return updatedAdmin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    // Delete an admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (adminService.deleteAdmin(id)) {
            return ResponseEntity.noContent().build(); // Return 204 if deleted
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }
}
