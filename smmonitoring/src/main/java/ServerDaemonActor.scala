import akka.actor.Actor

package SMServerMonitoring {

  class ServerDaemonActor extends Actor{

    private val MaxRetryForResponse = 3
    private var HeartbeatInterval = 5 //In Minutes
    
    def receive = { 
      
      case msg @ StatusAlive(payload) => 
      	
      
    }
  }
}