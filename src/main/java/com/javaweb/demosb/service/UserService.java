package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.UserDTO;
import com.javaweb.demosb.entity.UserEntity;
import com.javaweb.demosb.exception.BusinessException;
import com.javaweb.demosb.repository.UserRepository;
import com.javaweb.demosb.util.DataUtils;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final AuditorAware<String> auditorAware;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(AuditorAware<String> auditorAware, UserRepository userRepository, ModelMapper modelMapper) {
        this.auditorAware = auditorAware;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUserByUserName(String userName) {
        Optional<UserEntity> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    public UserDTO finishUserProfile(Integer weight, Integer height, Integer age, String gender, String userName) {
        if (DataUtils.isNullOrZero(weight) || DataUtils.isNullOrZero(height) || DataUtils.isNullOrZero(age) || DataUtils.isNullOrEmpty(gender)) {
            throw new BusinessException("INVALID_INPUT");
        }

        userName = getUserByUserName(userName).getUserName();
        Optional<UserEntity> user = userRepository.finishUserProfile(weight, height, age, gender, userName);

        return modelMapper.map(user, UserDTO.class);
    }

    //Mai lam tiep
    @Transactional
    public UserDTO saveUser(UserDTO dto){
        UserEntity model;
        if (dto.getUserId() != null){
            model = userRepository.findById(dto.getUserId()).orElseThrow(() -> new BusinessException("USER_NOT_FOUND"));
            model.setWeight(dto.getWeight());
            model.setHeight(dto.getHeight());
            model.setAge(dto.getAge());
            model.setGender(dto.getGender());
            userRepository.save(model);
            return modelMapper.map(model, UserDTO.class);
            }
        return null;
        }
    public UserDTO getCurrentUser() {
        String currentUserName = auditorAware.getCurrentAuditor().orElse(null);
        if (currentUserName != null) {
            return getUserByUserName(currentUserName);
        }
        return null;
    }
}
