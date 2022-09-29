package com.test.projects.mimimi.service.subjects;

import com.test.projects.mimimi.dto.VotePairDTO;
import com.test.projects.mimimi.dto.VoteSubjectDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/*
    Немножечко хардкода просто для прототипирования
*/

/**
 *  Сервис для кеширования и выдачи списка пар для голосования без использования базы данных
 *  @return возвращает именно ArrayList для рандомизирования
 */

@Service("subjectStorageFromHardCode")
public class SubjectStorageHardCodeImpl implements SubjectStorage {
    private final Map<String, ArrayList<VotePairDTO>> votePairs = new HashMap<>();

    @PostConstruct
    private void fillVotePairs() {
        VoteSubjectDTO firstSubject = new VoteSubjectDTO(UUID.randomUUID(), "Барсик", "https://icdn.lenta.ru/images/2021/12/13/00/20211213002603327/square_320_6df6f5217f0f0b3e856b6a0276c1d270.jpg", "Кошки", false);
        VoteSubjectDTO secondSubject = new VoteSubjectDTO(UUID.randomUUID(), "Пушок", "https://images.eksmo.ru/upload/iblock/b34/cat-_1_.png", "Кошки", false);
        VoteSubjectDTO thirdSubject = new VoteSubjectDTO(UUID.randomUUID(), "Рыжик", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Felis_silvestris_silvestris.jpg/1200px-Felis_silvestris_silvestris.jpg", "Кошки", false);
        VoteSubjectDTO forthSubject = new VoteSubjectDTO(UUID.randomUUID(), "Матроскин", "https://go64.ru/upload/quickly/cat-2143332_1280.jpg", "Кошки", false);


        ArrayList<VotePairDTO> votePairs = new ArrayList<>();
        votePairs.add(new VotePairDTO(firstSubject, secondSubject));
        votePairs.add(new VotePairDTO(firstSubject, thirdSubject));
        votePairs.add(new VotePairDTO(firstSubject, forthSubject));
        votePairs.add(new VotePairDTO(secondSubject, thirdSubject));
        votePairs.add(new VotePairDTO(secondSubject, forthSubject));
        votePairs.add(new VotePairDTO(thirdSubject, forthSubject));
        this.votePairs.put("Кошки", votePairs);
    }

    @Override
    public ArrayList<VotePairDTO> getVotePairs(String subjectCategories) throws ObjectNotFoundException {
        ArrayList<VotePairDTO> votePairsDTOs = votePairs.get(subjectCategories);
        if(votePairsDTOs == null) {
            throw new ObjectNotFoundException("Subjects of this category not founded");
        }
        return votePairsDTOs;
    }
}
