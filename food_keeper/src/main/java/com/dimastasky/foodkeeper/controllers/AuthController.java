package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.account.Role;
import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.data.ERole;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseType;
import com.dimastasky.foodkeeper.models.dto.userDTO.UserLoginDTO;
import com.dimastasky.foodkeeper.models.dto.userDTO.UserCreationDTO;
import com.dimastasky.foodkeeper.payload.response.JwtResponse;
import com.dimastasky.foodkeeper.payload.response.MessageResponse;
import com.dimastasky.foodkeeper.repository.RoleRepository;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseTypeRepository;
import com.dimastasky.foodkeeper.security.jwt.JwtUtils;
import com.dimastasky.foodkeeper.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    WarehouseTypeRepository warehouseTypeRepository;

    @GetMapping("/all-users")
    //@PreAuthorize("hasRole('MODERATOR')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // todo: Return JWT response OR DTO ?
    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getFullname(),
                userDetails.getEmail(), roles);
    }

    @PostMapping("/user")
    //@PreAuthorize("hasRole('MODERATOR')") // Убрать, если нужно зарегать модератора
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreationDTO signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                signupRequest.getFullname(),
                encoder.encode(signupRequest.getPassword())
                );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found!!!."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }

            });
        }
        user.setRoles(roles);

        // TODO: Убрать инициализацию склада, инициализировать склад отдельно
        Set<Warehouse> warehouses = new HashSet<>();
        WarehouseType warehouseType = warehouseTypeRepository.getReferenceById(1);
        Warehouse initWarehouse = new Warehouse("Склад "+ user.getFullname(), warehouseType);
        warehouses.add(initWarehouse);
        user.setWarehouses(warehouses);

        warehouseRepository.save(initWarehouse);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
