package mx.com.odraudek99.notificaciones;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class Cron {

	
	
	@Autowired
	TwitterInegration twitterInegration;

	@Scheduled(cron = "${cron.expression.twitt}")
	public void cronTaskTwitter() {
		twitterInegration.init();
	}
	
	@Scheduled(cron = "${cron.expression}")
	public void cronTaskExample() {
		System.out.println("tes.....");
	}
}