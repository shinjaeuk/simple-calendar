package com.careerfoundar.simplecalendar.web;

import com.careerfoundar.simplecalendar.domain.Mentor;
import com.careerfoundar.simplecalendar.dto.AgendaDto;
import com.careerfoundar.simplecalendar.dto.AgendaDto.Response;
import com.careerfoundar.simplecalendar.service.MentorService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class AgendaController {

    @NonNull
    private final MentorService mentorService;

    @GetMapping("mentors/{id}/agenda")
    public ResponseEntity<Response> getAgenda(@PathVariable("id") long id) {

        Mentor mentor = mentorService.getMentor(id);

        return  ResponseEntity.ok(AgendaDto.Response.of(mentor));
    }

    @PostMapping("mentors/{id}/agenda/{agendaId}")
    public ResponseEntity<String> createCall(@PathVariable("id") long id, @PathVariable("agendaId") long agendaId, @RequestBody AgendaDto.Create agenda) {

        log.info("id:{}, agenda id:{}, agenda: {}", id, agendaId, agenda);

        mentorService.createCall(id, agendaId, agenda.getReason());

        return ResponseEntity.ok("ok");
    }
}
