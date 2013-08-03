import akka.actor.{Actor, ActorRef}
import scala.util.parsing.json.JSONObject

package SMServerMonitoring {
  class UserSession(UserName: String, storage: ActorRef) extends Actor {

	private val loginTime = System.currentTimeMillis
	private var userLog: List[String] = Nil
	
    def receive = { 
      
      case msg @ Register(hostname : String, ip : String, token : String) => 
    	
       
    }

  }
}
