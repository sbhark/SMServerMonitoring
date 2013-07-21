import akka.actor.ActorRef
import akka.actor.Actor
import com.mongodb.casbah.MongoConnection

package SMServerMonitoring {

  class DatabaseWrapper extends Actor {

    private var Logger : ActorRef = null 
    private var dbConnection : MongoConnection = null
    
    def receive = { 
      
      case msg @ Initialize(logger) => { 
        
        initialize(logger)
      }
      
      case msg @ WriteToDB() => { 
        
      }
      
      case msg @ TerminateActor(message) => { 
        
      }
      
    }
    
    def initialize(logger : ActorRef) {
    	
      //Have reference of system logger 
      Logger = logger 
      logger ! WriteToLog("Starting up DatabaseWrapper", "info")
      
      //Establish Connection with database.
      dbConnection = MongoConnection()
      
      //Keep connection with database open. 
    }
    
    def ExecuteStatement(statement : String) { 
      
      
    }
    
    private def terminate() { 
      
      //Close connection with database. 
      
    }
  }
}