package org.apache.spark.examples.iceke

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by iceke on 17/5/16.
  */
object SparkSort {
  def main(args: Array[String]){
    if(args.length<2){
      System.err.println("Usage of Parameters: ApplicationName inputPath")
      System.exit(1)
    }
    val sparkConf = new SparkConf().setAppName(args(0))
    val sparkContext = new SparkContext(sparkConf)
    //val sparkContext = FlintWCContext.create(sparkConf)
    val lines = sparkContext.textFile(args(1))
    val words = lines.flatMap(s => {
      val parts = s.split("\\s+")
      parts.map(Integer.parseInt(_)).map((_,1))
    })
    val results = words.reduceByKey(_+_).sortByKey()
    results.saveAsTextFile(args(1))
  }

}
