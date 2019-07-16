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
    println(keys)
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

  private def parseKey(args: Array[String]): Array[String] = {
    var consumer_pubKey: String = ""
    var consumer_secKey: String = ""
    var AccessToken_pub: String = ""
    var AccessToken_sec: String = ""

    return (consumer_secKey, consumer_secKey, AccessToken_pub, AccessToken_sec)
  }
}
