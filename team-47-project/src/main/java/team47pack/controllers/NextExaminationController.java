package team47pack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.NextProcedure;
import team47pack.service.NextExaminationService;

@RestController
public class NextExaminationController {
	
	@Autowired
	private NextExaminationService nextExamService;
	
    @GetMapping(value="/ca/getNextExamins")
    @PreAuthorize("hasRole('CADMIN')")
    public java.util.List<NextProcedure> getNextExamins(Principal user){
        return nextExamService.getNextExns(user.getName());
 
    }

}
