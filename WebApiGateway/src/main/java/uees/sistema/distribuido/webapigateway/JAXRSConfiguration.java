package uees.sistema.distribuido.webapigateway;

import java.util.Timer;
import java.util.TimerTask;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import uees.sistema.distribuido.service.LeaderElectionService;

@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {

    public JAXRSConfiguration(){
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new LeaderElectionTask(), 0, 60000); // Ejecuta cada 5 segundos
    }
    
}

class LeaderElectionTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Eligiendo lider...");
            LeaderElectionService.getInstance().electLeader();
        }
    }