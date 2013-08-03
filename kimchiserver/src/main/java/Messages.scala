
import akka.actor.ActorRef

package SMServerMonitoring {

  sealed trait ExchangeMessages

  //Messages sent to local actors. 
  case class Initialize(Logger : ActorRef) extends ExchangeMessages
  case class Register(hostname : String, ip : String, token : String) extends ExchangeMessages
  case class Remove(hostname : String) extends ExchangeMessages 
  case class WriteToLog(logMessage : String, logLevel : String) extends ExchangeMessages
  case class TerminateActor(reason : String) extends ExchangeMessages //Don't use to terminate remote actors
  case class SendAlert(message : String) extends ExchangeMessages
  case class WriteToDB() extends ExchangeMessages
  
  //Messages sent to monitoring machine. 
  case class StatusCheck(payload : String) extends ExchangeMessages 
  
  //Messages sent from monitoring machine. 
  case class StatusAlive(payload : String) extends ExchangeMessages

}