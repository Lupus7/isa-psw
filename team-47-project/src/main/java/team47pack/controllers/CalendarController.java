package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.dto.CalendarEvent;
import team47pack.service.CalendarService;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@RestController
@RequestMapping(value="/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping(value="/schedule")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('NURSE')")
    public List<CalendarEvent> schedule() {
        return null;
    }

    @GetMapping(value="/doc-info/{email}/{date}")
    @PreAuthorize("hasRole('PATIENT')")
    public List<CalendarEvent> docInfo(@PathVariable(value="email") String email, @PathVariable(value="date") String date) {
        return calendarService.docInfo(email, date);
    }

    @GetMapping(value="/room-info")
    @PreAuthorize("hasRole('CADMIN')")
    public List<CalendarEvent> roomInfo() {
        return null;
    }
}
