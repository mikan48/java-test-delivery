package com.example.test_delivery.services;

import com.example.test_delivery.dto.UserDto;
import com.example.test_delivery.entities.Roles;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.entities.UserStatus;
import com.example.test_delivery.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto registerUser(UserDto userDto) {
        UserEntity newUser = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        modelMapper.map(userDto, user);
        UserEntity updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    public List<UserDto> getUsersByRole(Roles role) {
        return userRepository.findByRole(role)
                .stream().map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }


    public void deactivateUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        user.setStatus(UserStatus.DISABLED);
        userRepository.save(user);
    }
}
