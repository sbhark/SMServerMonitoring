import java.text.SimpleDateFormat
import java.util.Calendar
import akka.actor.Props
import akka.actor.ActorRef
import SMServerMonitoring._

object Main {
  //Main script where all classes are initialized and actors started.
  
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
  
  def terminateAll() {
    println("-----SMServerMonitoring-----")
    println("Terminating application, shutting down all services...")
	println("Application termination complete.")
  }
  
  def getCurrentTime() : String = { 
    val currentTime = dateFormat.format(Calendar.getInstance().getTime())
    return currentTime
  }
  
  def getInitializationSettings(db : ActorRef) { 
    
    //Connect to db 
    
    //Get 
  }

  def main(args : Array[String]) { 
    
    //Start Logger
    val system = akka.actor.ActorSystem()
    val logger = system.actorOf(Props[Logger])
    logger ! Initialize(logger) //Passing in null for logger initialization as we don't need to pass anything in

    //Start Server Daemon Actor

    //Initialize Alert Actor
    val alert = system.actorOf(Props[AlertActor])
    alert ! Initialize(logger)

    //Initalize DatabaseWrapper Actor


    //Create application directory in /etc/smservermonitoring/
    val applicationDirectory = "/etc/smservermonitoring/"

    //Create application authentication file in /etc/smservermonitoring/creds/AllowedHosts.conf
    val applicationAuthFile = "/etc/smservermonitoring/creds/AllowedHosts.conf"

    //Create application config file in /etc/smservermonitoring/conf/AppConfig.conf
    val applicationConfigFile = "/etc/smservermonitoring/conf/AppConfig.conf"
    
    
    //Load initialization settings from database 

    //start services here

    println("Components initialization complete.")
  	println("Application is running and listening on port 9440.")

  	Thread.sleep(5000);
    System.exit(0);
  }
}