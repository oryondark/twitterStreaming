package kr.kookmin.hjkimSpkStreaming
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import org.apache.spark.SparkConf._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkInst{
  /*
  Spark Streaming Usage:
  Please, inputs the arguments keys to access your twitter.


  Usage:
  ./bin/spark-submit \
    --class kr.kookmin.hjkimSpkStreaming.SparkInst \
    --master local[0]
    /path/to/your/app
    argument1 argument2
  */
  def main(args: Array[String]): Unit = {
    StreamingExamples.setStreamingLogLevels()
    var keys = parseKey(args);
    println(keys.mkString(","));

    //Spark Streaming Context
    val sparkConf = new SparkConf().setAppName("NetworkWordCount")
    var ssc = new StreamingContext(sparkConf, Seconds(1));
    wordCount_Example(ssc);
    ssc.start();
    ssc.awaitTermination();

    /*
    var twitter_instance = new TwitterFactory().getInstance()
    //Consumer auth assigning for accessing twitter
    twitter_instance.setOAuthConsumer(consumer_pubKey, // Consummer key
                                      consumer_secKey);  // Consumer Secret Key

    //Send Access token
    twitter_instance.setOAuthAccessToken(new AccessToken(
                                      AccessToken_pub, AccessToken_sec))

    var listen_homeTimeLine = twitter_instance.getHomeTimeline
    println(listen_homeTimeLine)
    */

  }

  private def wordCount_Example(ssc: StreamingContext): Unit = {
    var lines = ssc.socketTextStream("10.160.0.4", 9999, StorageLevel.MEMORY_AND_DISK_SER);
    var words = lines.flatMap(_.split(" "));

    var pairs = words.map(word => (word, 1));
    var wordCounts = pairs.reduceByKey(_ + _);
    wordCounts.print();

  }

  private def parseKey(args: Array[String]): Array[String] = {
    var consumer_pubKey: String = args(0);
    var consumer_secKey: String = args(1);
    var accessToken_pub: String = args(2);
    var accessToken_sec: String = args(3);

    return Array(consumer_pubKey, consumer_secKey, accessToken_pub, accessToken_sec)
  }
}
