import java.io.File
import com.typesafe.config.{ Config, ConfigFactory }
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

class LoadProperties {

  val logger = LoggerFactory.getLogger("LoadProperties");
    
  def loadPropertiesFromConf(): Config = {
    val configPath = System.getProperty("config.path")
    logger.debug("Useful message....")
    if (configPath == null)
      return null;

    logger.debug("config.path: " + configPath)
    println("config.path: " + configPath)
    val config = ConfigFactory.parseFile(new File(configPath))

    return config;
  }
}

object LoadPropertiesTester {
  
  val loggerTest = LoggerFactory.getLogger("LoadPropertiesTester");


  def main(args: Array[String]) {
    val proploader = new LoadProperties();
    val config = proploader.loadPropertiesFromConf()
    loggerTest.debug(config.toString());
    println(config.toString())
    val noOfIterationsToRetain = config.getString("ncsloadApp.noOfIterationsToRetain")
    if (!config.isEmpty()) {
      val ncsConfig = config.getConfig("ncsloadApp");
      loggerTest.debug(ncsConfig.getString("noOfIterationsToRetain"));
      println(ncsConfig.getString("noOfIterationsToRetain "));
    }
    loggerTest.debug("noOfIterationsToRetain : " + noOfIterationsToRetain );
    println("noOfIterationsToRetain : " + noOfIterationsToRetain );

  }
}