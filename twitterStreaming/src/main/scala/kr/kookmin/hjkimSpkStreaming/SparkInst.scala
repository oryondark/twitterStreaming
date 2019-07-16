package kr.kookmin.hjkimSpkStreaming
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.api.java.function._


object SparkInst{
  /*
  Spark Streaming Usage:
  Please, inputs the arguments keys to access your twitter.
  */
  def main(args: Array[String]): Unit = {

    var keys = parseKey(args);
    println(keys.mkString(","));

    //Spark Streaming Context
    var ssc = new StreamingContext("10.160.0.4", "NetworkWordCount", Seconds(1));
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

  private def wordCount_Example(ssc: Unit): Unit = {
    var lines = ssc.socketTextStream("10.160.0.4", 9999);
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
