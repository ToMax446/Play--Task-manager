package data.services;

import data.domain.PeopleDO;
import data.dto.PeopleDTO;

import java.util.UUID;

public class PeopleMapper {
    public static PeopleDTO toTransfer(PeopleDO domain) {
        System.out.println("toTransfer in mapper");
        PeopleDTO dto = new PeopleDTO();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setEmails(domain.getEmails());
        dto.setFavoriteProgrammingLanguage(domain.getFavoriteProgrammingLanguage());

        return dto;
    }

    public static PeopleDO fromTransfer(PeopleDTO dto) {
        System.out.println("fromTransfer in mapper");
        PeopleDO domain = new PeopleDO();
        domain.setId(UUID.randomUUID().toString());
//        domain.setId(dto.getId());
        domain.setName(dto.getName());
        domain.setEmails(dto.getEmails());
        domain.setFavoriteProgrammingLanguage(dto.getFavoriteProgrammingLanguage());
        System.out.println(domain.getId() + domain.getName() + domain.getEmails() + domain.getFavoriteProgrammingLanguage());

        return domain;
    }

}
