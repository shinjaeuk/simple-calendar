package com.careerfoundar.simplecalendar.service;

import com.careerfoundar.simplecalendar.repository.MentorRepository;
import com.careerfoundar.simplecalendar.domain.Calendar;
import com.careerfoundar.simplecalendar.domain.Mentor;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class MentorService {

    @NonNull
    private final MentorRepository mentorRepository;

    public Mentor getMentor(long id) {

        Optional<Mentor> mentor = mentorRepository.findById(id);

        if(!mentor.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Mentor");

        return mentor.get();
    }

    public void createCall(long id, long calendarId, String reason) {

        Mentor mentor = mentorRepository.getOne(id);

        List<Calendar> calendars = mentor.getCalendars();

        Optional<Calendar> calendarOptional = calendars.stream().filter(c -> c.getId().equals(calendarId)).findFirst();

        if(!calendarOptional.isPresent()) throw new RuntimeException("No calendar");

        Calendar calendar = calendarOptional.get();

        if(!calendar.getAvailable()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Not Available Time");

        calendar.setAvailable(false);
        calendar.setReason(reason);

        mentorRepository.save(mentor);
    }
}
