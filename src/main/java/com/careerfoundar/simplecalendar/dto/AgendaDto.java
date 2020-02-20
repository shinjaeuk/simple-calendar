package com.careerfoundar.simplecalendar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class AgendaDto {

    @Data
    public static class Response {

        private Mentor mentor;
        private List<Calendar> calendars;

        @Data
        public static class Mentor {
            private long id;
            private String name;
        }

        @Data
        public static class Calendar {

            private long id;

            private Boolean available;

            @JsonProperty("date_time")
            private Timestamp dateTime;
        }

        public static  AgendaDto.Response of(com.careerfoundar.simplecalendar.domain.Mentor mentor) {

            AgendaDto.Response agendaDto = new AgendaDto.Response();

            agendaDto.mentor = new AgendaDto.Response.Mentor();
            agendaDto.mentor.setId(mentor.getId());
            agendaDto.mentor.setName(mentor.getName());
            agendaDto.calendars =  mentor.getCalendars().stream().map(c -> {
                Calendar calendar = new Calendar();
                calendar.setId(c.getId());
                calendar.setAvailable(c.getAvailable());
                calendar.setDateTime(c.getDateTime());

                return calendar;
            }).collect(Collectors.toList());

            return agendaDto;
        }
    }

    @Data
    public static class Create {

        private String reason;
    }
}
