import java.text.SimpleDateFormat
import java.util.Calendar
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.File
import akka.actor.Actor

package SMServerMonitoring {
  class Logger extends Actor {

    private var absoluteLogPathFile = ""
    private val spaces = "          "
    private var initialized = 0;
    
    def receive = { 
      
      case msg @ WriteToLog(logMessage, logLevel) => 
        val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val currentTime = dateFormat.format(Calendar.getInstance().getTime())
        WriteToLog(currentTime, logMessage, logLevel)

      //Note: Logger will always be the last actor to be terminated. 
      case msg @ TerminateActor(terminateMessage) => 
        self ! akka.actor.PoisonPill
        terminateLogger(terminateMessage)
        
      case msg @ Initialize(logger) => 
        if (initialized == 0) { 
    	  initialize() 
        }
    }  
      
    def initialize() {
      
      initialized += 1
      
      if (initialized > 1) { 
        println("Logger already initialized no need to initialized.")
      } else { 
        println("Initializing logger...")
  
        val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val formattedTimeStamp = dateFormat.format(Calendar.getInstance().getTime())
  
        //Default log directory is located in /var/log/smmonitoring/
        val logDirectory = "/var/log/smmonitoring"
  
        //Default log file name is monitoring.log
        val logFile = "monitoring.log"
  
        //Check if logging directory exists
        try { 
  	      val directory = new File(logDirectory)
  	      if (!directory.exists()) {
  	        println("Log directory not found creating directory...")
  	        directory.mkdirs();
  	      } else {
  	        println("Log directory exists, using existing directory...")
  	      }
        } catch { 
          case exception: Exception => println("Failed to create log directory, are you logged in as user root?")
        }
  
        //Check if log file exists, if so just append to it.
        val file = new File(logDirectory + "/" + logFile)
        if (!file.exists()) {
          println("Log file not found creating file....")
        } else {
          println("Log file found, appending to existing log file...")
        }
  
        //Set absolute log directory path
        absoluteLogPathFile = file.getAbsolutePath
  
        //Initialize and start filewriter to not constantly create a new writer.
        try { 
          WriteToLog(formattedTimeStamp, "---LOGGER STARTED---", "info")
        } catch { 
          case exception: Exception => println("Failed to write to log file, are you logged in as user root?")
        }
      }
    }
    
    def WriteToLog(TimeStamp : String, LogMessage : String, LogLevel : String) {

      var logLevelIndicator = ""
        
      LogLevel match {
        case "debug" => logLevelIndicator = " ---DEBUG--- "
        case "info" => logLevelIndicator = " ---INFO--- "
        case "warning" => logLevelIndicator = " ---WARNING--- "
        case "alert" => logLevelIndicator = "---ALERT---"
      }

      val logWriter = new BufferedWriter(new FileWriter(absoluteLogPathFile, true))
      logWriter.write(TimeStamp+ " " + logLevelIndicator + LogMessage)
      logWriter.newLine()
      logWriter.flush()
      logWriter.close()
    }

    def terminateLogger(LogMessage : String){

      
    }
  }
}