package com.example.bl_lab1.service.impl;

import com.example.bl_lab1.model.User;
import com.example.bl_lab1.model.UsersList;
import com.example.bl_lab1.service.UserService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private static final String PATH_TO_XML = "users.xml";
    private final File file = new File(PATH_TO_XML);
    public User getUserByLogin(String login) throws JAXBException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    
    public List<User> getAllUsers() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        //We had written this file in marshalling example
        UsersList usersList = (UsersList) jaxbUnmarshaller.unmarshal(new File(PATH_TO_XML));
        return usersList.getUsers();
    }
    
    @Override
    public void createUser(User user) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UsersList usersList = (UsersList) unmarshaller.unmarshal(new File(PATH_TO_XML));
        List<User> userList = getAllUsers();
        userList.add(user);
        
        usersList.setUsers(userList);
        
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(usersList, file);
    
    }
    
    
}
