package com.example.denttech.service.impl;

import com.example.denttech.dto.request.ItemRequestDTO;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.CompanyResponseDTO;
import com.example.denttech.dto.response.ItemResponseDTO;
import com.example.denttech.dto.response.UserResponseDTO;
import com.example.denttech.exception.EntityNotFoundException;
import com.example.denttech.model.Company;
import com.example.denttech.model.Item;
import com.example.denttech.model.User;
import com.example.denttech.repository.CompanyRepository;
import com.example.denttech.repository.ItemRepository;
import com.example.denttech.repository.UserRepository;
import com.example.denttech.service.ItemService;
import com.example.denttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    final ItemRepository itemRepository;
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final CompanyRepository companyRepository;

//    @Override
//    public List<UserResponseDTO> getUsers(String username) {
//        return userRepository.findAll()
//                             .stream()
//                             .map(user -> {
//                                         //TODO PARENT WILL BE SAME OF COMPANY ID FROM THE USER
////                                    if (company.getParent() != null) {
////                                        return
////                                    }
////                                    return null;
//                                         return modelMapper.map(user, UserResponseDTO.class);
//                                     }
//                             )
//                             .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserResponseDTO getUserById(Long id, String username) {
//
//        return modelMapper.map(userRepository.findById(id)
//                                             .orElseThrow(() -> new EntityNotFoundException("Customer not found")), UserResponseDTO.class);
//
//    }
//
//
//
//    @Override
//    public UserResponseDTO editUser(UserRequestDTO userRequestDTO, String username, Long id) {
//        User c1 = modelMapper.map(userRequestDTO, User.class);
//        User c = userRepository.findById(id)
//                                     .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
//
//            c.setEmail(c1.getEmail());
//            c.setFirstName(c1.getFirstName());
//            c.setLastName(c1.getLastName());
//            c.setTel(c1.getTel());
//            return modelMapper.map(userRepository.save(c), UserResponseDTO.class);
//
//    }

    @Override
    public List<ItemResponseDTO> getItems(String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        return itemRepository.findByCompany(u.getCompany())
                             .stream()
                             .map(item -> modelMapper.map(item, ItemResponseDTO.class))
                             .collect(Collectors.toList());
    }

    @Override
    public ItemResponseDTO getItemById(Long id, String username) {
        return modelMapper.map(itemRepository.findById(id)
                                             .orElseThrow(), ItemResponseDTO.class);
    }

    @Override
    public ItemResponseDTO save(ItemRequestDTO itemRequestDTO, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();
        Item item=modelMapper.map(itemRequestDTO,Item.class);
        item.setCompany(u.getCompany());
        return modelMapper.map(itemRepository.save(item), ItemResponseDTO.class);
    }

    @Override
    public void deleteItem(Long id, String username) {
        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        Item item = itemRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        if (u.getCompany() == item.getCompany()) {
            companyRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("this company doesnt belong to you ");
        }
    }

    @Override
    public ItemResponseDTO editItem(ItemRequestDTO itemRequestDTO, String username, Long id) {

        User u = userRepository.findByEmail(username)
                               .orElseThrow();

        Item item = itemRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        if (u.getCompany() == item.getCompany()) {

            Item newItem = modelMapper.map(itemRequestDTO, Item.class);
            item.setName(newItem.getName());
            item.setDescription(newItem.getDescription());
            item.setPrice1(newItem.getPrice1());
            item.setPrice2(newItem.getPrice2());
            item.setPrice3(newItem.getPrice3());
            item.setPrice4(newItem.getPrice4());
            return modelMapper.map(itemRepository.save(item), ItemResponseDTO.class);
        } else {
            throw new EntityNotFoundException("this company doesnt belong to you ");
        }


    }
}
