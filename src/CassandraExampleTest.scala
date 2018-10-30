

import com.datastax.spark.connector._
import org.apache.spark._
import org.apache.spark.SparkContext._
//import org.apache.spark.sql.SQLContext 
import scala.reflect.api.materializeTypeTag




case class EmpNameToCity(emp_name: String, emp_city: String)
  

object CassandraExampleTest {
  
  
    /** Our main function where the action happens */
  def main(args: Array[String]) { 
  
  // connect to a local cassandra instance     
    val conf = new SparkConf(true)
        .set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext("local", "test", conf)
     
    val emp_rdd = sc.cassandraTable("suhas_kys", "emp")    
    println(">>>>>>>>>>>>>>>>>>>>>> ### emp_rdd   " + emp_rdd.first().dataAsString)
    emp_rdd.foreach(println)
    
    
    val emp_name_city_rdd = emp_rdd.map(r => new EmpNameToCity(r.getString("emp_name"), r.getString("emp_city")))   
    emp_name_city_rdd.saveToCassandra("suhas_kys", "emp_city")
    
    println(" emp_name_city_rdd count ------>" + emp_name_city_rdd.count()) 
    
    
    
    // with SQLContext
/*     val sqlContext = new SQLContext(sc)
    
    
    // read in some data as a DataFrame
    val df = sqlContext
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "emp", "keyspace" -> "suhas_kys")).load.cache()
  
     df.show()
     */
     
           
  }
  
}