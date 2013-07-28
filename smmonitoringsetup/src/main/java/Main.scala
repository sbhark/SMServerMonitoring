import scala.sys.process.Process
object Main {
  
  /** 
   *  Connect to DB
   */
  private def connectToDB() { 
    
    //Support for remote host in future for now db is in same machine as monitoring master.
    //val connection = MongoConnection( List("localhost"))
    
    //val database = connect("smmonitoring")
  }
  
  /** 
   *  Initial Setup for Server 
   */
  private def initialSetupServer() { 
    
    println("Starting setup of new server...\n")
    //Create New Database 
    println("Establishing connection to db")
    
    
  }
  
  /** 
   *  Adding a node to database 
   */
  private def addNode() { 
    
    println("Starting process to add new monitoring node...")
    //Connect to database namespace nodes
    
    //Add host info 
    //If host info already exist inform and ask steps again
    
    //Close database connection 
  }
  
  def main(args : Array[String]) { 
    
    println("*********************************************")
    println("****** SMServerMonitoring Server Setup ******\n")
    println("\n")
    
    println("Checking if MongoDB is installed on system...")
    val checkMongo = Process("/etc/init.d/mongodb", Seq("status")).!!
    if (checkMongo.contains("unrecgonized service")) { 
      println("MongoDB is not installed, please install MongoDB as it is required. \nExiting setup...")
      System.exit(0)
    }
    
    println("Hey there! Just a few questions below to setup your monitoring server. \n")
    println("At any time during input press q to quit setup\n")
    println("Please select from the following options: \n")
    println("1 - Setting up a new monitoring server \n") 
    println("2 - Adding new nodes to monitor \n") 
    
    for ( input <- io.Source.stdin.getLines ) { 
      input match { 
        case "1" => initialSetupServer()
        case "2" => addNode()
        case "q" => println("Exiting setup. Good Bye!"); System.exit(0)
        case _ => println("Yo that is not valid option, try again")
      }
    }
      
  }
}
