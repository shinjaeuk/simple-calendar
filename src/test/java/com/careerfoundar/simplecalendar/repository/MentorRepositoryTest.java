package com.careerfoundar.simplecalendar.repository;

import com.careerfoundar.simplecalendar.domain.Calendar;
import com.careerfoundar.simplecalendar.domain.Mentor;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
class MentorRepositoryTest {

    @Autowired
    private MentorRepository mentorRepository;

    @Test
    void select_mentors() {

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("jushin");
        mentor.setCreatedUser("admin");
        mentor.setModifiedUser("admin");

        mentorRepository.save(mentor);

        Calendar calendar = new Calendar();
        calendar.setId(1L);
        calendar.setAvailable(true);
        calendar.setDateTime(new Timestamp((new Date().getTime())));
        calendar.setMentor(mentor);

        mentor.getCalendars().add(calendar);;


        List<Mentor> mentors = mentorRepository.findAll();

        mentor.getCalendars().stream().forEach(c-> System.out.println(c.toString()));
    }
}