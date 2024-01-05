package com.example.denttech.service.impl;

import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.UserResponseDTO;
import com.example.denttech.exception.EntityNotFoundException;
import com.example.denttech.model.Company;
import com.example.denttech.model.User;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.repository.UserRepository;
import com.example.denttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final CompanyRepository companyRepository;

    @Override
    public List<UserResponseDTO> getUsers(String username) {
        return userRepository.findAll()
                             .stream()
                             .filter(user ->
                                     user.getUserRole()
                                         .getId() != 1
                             )
                             .map(user -> {
                                         //TODO PARENT WILL BE SAME OF COMPANY ID FROM THE USER
//                                    if (company.getParent() != null) {
//                                        return
//                                    }
//                                    return null;

                                         return modelMapper.map(user, UserResponseDTO.class);
                                     }
                             )
                             .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id, String username) {
        return modelMapper.map(userRepository.findById(id)
                                             .orElseThrow(() -> new EntityNotFoundException("Customer not found")), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        User c = modelMapper.map(userRequestDTO, User.class);

        Company com = companyRepository.findById(userRequestDTO.getCompany())
                                       .orElseThrow();
        c.setCompany(com);
        return modelMapper.map(userRepository.save(c), UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long id, String username) {

    }

    @Override
    public UserResponseDTO editUser(UserRequestDTO userRequestDTO, String username, Long id) {
        User c1 = modelMapper.map(userRequestDTO, User.class);
        User c = userRepository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        c.setEmail(c1.getEmail());
        c.setFirstName(c1.getFirstName());
        c.setLastName(c1.getLastName());
        c.setTel(c1.getTel());
        return modelMapper.map(userRepository.save(c), UserResponseDTO.class);

    }

    @Override
    public List<UserResponseDTO> getBuyers(String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        return userRepository.findByVendor(3, false, u.getCompany()
                                                      .getId())
                             .stream()
                             .map(user -> modelMapper.map(user, UserResponseDTO.class)
                             )
                             .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getVendors(String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        return userRepository.findByVendor(2, true, u.getCompany()
                                                     .getId())
                             .stream()
                             .map(user -> modelMapper.map(user, UserResponseDTO.class)
                             )
                             .collect(Collectors.toList());
    }
}
