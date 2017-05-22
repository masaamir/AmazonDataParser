/**
  * Created by maaamir on 5/22/17.
  */
import scala.collection.mutable.ListBuffer
class Product {
  var Id:Long=0
  var ASIN:String=""
  var productStatus:Boolean=false
  var title:String=""
  var group:String=""
  var salesRank:Long=0L
  var similarity:ListBuffer[String]=new ListBuffer[String]()
  var categories:ListBuffer[String]=new ListBuffer[String]()
  var reviewsCount:Int=0
  var downloaded:Int=0
  var avgRating:Double=0.0
  var reviews:ListBuffer[Review]=new ListBuffer[Review]()

}
