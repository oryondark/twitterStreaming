package kr.kookmin.hjkimSpkStreaming
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

/**
 * @author ${user.name}
 */
object scalaTest{
  

  def main(args: Array[String]): Unit = {

    var consumer_pubKey: String = ""
    var consumer_secKey: String = ""
    var AccessToken_pub: String = ""
    var AccessToken_sec: String = ""

    var twitter_instance = new TwitterFactory().getInstance()
    //Consumer auth assigning for accessing twitter
    twitter_instance.setOAuthConsumer(consumer_pubKey, // Consummer key
                                      consumer_secKey);  // Consumer Secret Key

    //Send Access token
    twitter_instance.setOAuthAccessToken(new AccessToken(
                                      AccessToken_pub, AccessToken_sec))

    var listen_homeTimeLine = twitter_instance.getHomeTimeline
    println(listen_homeTimeLine)

  }
}
