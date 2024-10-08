package com.adda.picture_sale_backend.services;

import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.entities.ConfirmCode;

import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.repositories.ConfirmCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ConfirmCodeService {

    @Autowired
    ConfirmCodeRepository confirmCodeRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;
    public List<ConfirmCode> findAll(){
        return confirmCodeRepository.findAll();
    }

    public ConfirmCode create(ConfirmCode news)
    {
        Long id = sequenceGeneratorService.generateSequence(ConfirmCode.SEQUENCE_NAME);
        news.setId(id);
        return confirmCodeRepository.insert(news);
    }
    public boolean delete(Long id){

        confirmCodeRepository.deleteById(id);
        return  true;
    }

    public boolean deleteAll()
    {
        confirmCodeRepository.deleteAll();
        return true;
    }

    public ConfirmCode findById(Long id)
    {
        Optional<ConfirmCode> optional = confirmCodeRepository.findById(id);
        if(optional.isEmpty()) return null;
        return optional.get();
    }


    public ConfirmCode findBySecureCodeAndEmailAndType(String code, String email, String type){

        List<ConfirmCode> confirmCodeList = confirmCodeRepository.findAll();



        ConfirmCode found = null;
               // confirmCodeRepository.findBySecureCodeAndEmailAndType(code, email, type);

        for(ConfirmCode item: confirmCodeList){

            if(item.getEmail().equals(email) && item.getSecureCode().equals(code) && item.getType().equals(type))
            {
                found = item;
            }
        }

        if(found!= null){

            if(found.getStatus()== ConfirmCode.STATUS_CONFIRM_PENDING)
            {
                return found;
            }else
            {
                return null;
            }
        }

        return  null;

    }

    public  ConfirmCode findBySecureCodeAndPath(String code, String path){

        ConfirmCode confirmCode = confirmCodeRepository.findBySecureCodeAndPathRandomAndStatus(code, path, ConfirmCode.STATUS_CONFIRM_PENDING);
        if(confirmCode != null){
            confirmCode.setStatus(ConfirmCode.STATUS_CONFIRM_OK);

            String email = confirmCode.getEmail();
            User user = userService.findByEmail(email);
            user.setStatus(User.STATUS_CONFIRM);
            userService.updateUser(user);
            return confirmCodeRepository.save(confirmCode);

        }else
        {

            return null;
        }


    }

    public ConfirmCode update(ConfirmCode confirmCode)
    {
        Optional<ConfirmCode> optional = confirmCodeRepository.findById(confirmCode.getId());
        if(optional.isEmpty()) return null;
        ConfirmCode found = optional.get();
        found.setEmail(confirmCode.getEmail());
        found.setSecureCode(confirmCode.getSecureCode());
        found.setEmail(confirmCode.getEmail());
        return confirmCodeRepository.save(found);
    }
}
