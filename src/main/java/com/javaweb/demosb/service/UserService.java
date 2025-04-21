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

    @Transactional
    public UserDTO saveUser(UserDTO dto){
        UserEntity model;
        if (dto.getUserId() != null){
            model = userRepository.findById(dto.getUserId()).orElseThrow(() -> new BusinessException("USER_NOT_FOUND"));
            dto.setUserName(model.getUserName());
            dto.setPassword(model.getPassword());
            dto.setRole(model.getRole());
            dto.setEmail(model.getEmail());
            dto.setCreatedAt(model.getCreatedAt());
            if(DataUtils.isNullOrZero(dto.getWeight())){
                dto.setWeight(model.getWeight());
            }
            if(DataUtils.isNullOrZero(dto.getHeight())){
                dto.setHeight(model.getHeight());
            }
            if(DataUtils.isNullOrZero(dto.getAge())){
                dto.setAge(model.getAge());
            }
            if(DataUtils.isNullOrEmpty(dto.getGender())){
                dto.setGender(model.getGender());
            }
            dto.setBmi(DataUtils.calculateBMI(dto.getWeight(), dto.getHeight()));

            }
        model = modelMapper.map(dto, UserEntity.class);
        userRepository.save(model);
        return modelMapper.map(model, UserDTO.class);
    }

    public UserDTO getCurrentUser() {
        String currentUserName = auditorAware.getCurrentAuditor().orElse(null);
        if (currentUserName != null) {
            return getUserByUserName(currentUserName);
        }
        return null;
    }
}
