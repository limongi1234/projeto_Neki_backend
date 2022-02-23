package br.org.neki.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.org.neki.backend.service.UserService;


@Component
@EnableScheduling
public class ScheduleConfig 
{
    @Autowired
	private UserService userService;
	
	@Scheduled(cron = "0 0 6/18 * * * ")
    public void mailSender()
	{
		userService.enviaEmails();
	}
}
