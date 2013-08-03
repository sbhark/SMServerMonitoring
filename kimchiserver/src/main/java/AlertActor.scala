import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

package SMServerMonitoring {

  class AlertActor extends Actor{
	  
    private val localLogger = context.actorOf(Props[Logger], name="logger")
    private val alertMessageHeader = "Alert! Issue Detected \n"
    private var Logger : ActorRef = null
    
    def receive = { 
      
      case msg @ Initialize(logger) => 
        initialize(logger)
      
      case msg @ SendAlert(message) => 
        
      	//write to log and send message 
        alert(message);
        localLogger ! WriteToLog(alertMessageHeader + message, "warn")
        
      case msg @ TerminateActor(message) => 
        
    }
    
    private def initialize(logger : ActorRef) { 
      
      Logger = logger
      Logger ! WriteToLog("Alert Actor initialized", "info")
    }
    
    def alert(message : String) { 
      
      //Check which alert methods are allowed then send alerts using the allowed methods. 
      
    }
    
    private def terminate() { 
      
    }
  }

}