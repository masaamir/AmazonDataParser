import scala.collection.mutable.ListBuffer
import java.util.Date
import java.text.{DateFormat, SimpleDateFormat}

/**
  * Created by maaamir on 5/22/17.
  */
class DataReader {
  //var dataFile="/home/maaamir/myComputer/dDrive/DI-Europe/Data/tiny.txt"
  var dataFile="/home/maaamir/myComputer/dDrive/DI-Europe/Data/amazon-meta.txt"

  // convert string to date:
  def stringToDate(dateString: String): Date = {
    val updateDate = dateString.replaceAll("T", "").replaceAll("Z", "")
    val formatter: SimpleDateFormat = new SimpleDateFormat("yyyy-M-ddHH:mm:ss")
    val date: Date = formatter.parse(updateDate)
    return date
  }

  def readData(): Unit ={
    val lines=io.Source.fromFile(dataFile).getLines()
    val chunk:ListBuffer[String]=new ListBuffer[String]()
    val productList:ListBuffer[Product]=new ListBuffer[Product]()
    var p=new Product
    while(lines.hasNext){
      val line=lines.next().trim
      if(line.trim==""){
        //create new object
        p=new Product
      }
      else if(line.trim.contains("Id")){
        val idLine=line.split(":")
        p.Id=idLine(1).trim.toInt
        println("Id is ::"+p.Id)
        //val ASINLine=lines.next()
        //println("test::"+lines.next().trim)
        p.ASIN=lines.next().trim.split(":")(1).trim
        //check status of product
        val status=lines.next().trim
        if(status.contains("discontinued product")){
          p.productStatus=false
          productList += p
          p=new Product
        }
        else if (status.contains("title")){
          p.title=status.split(":")(1).trim
          p.group=lines.next().trim.split(":")(1).trim
          p.salesRank=lines.next().trim.split(":")(1).trim.toLong
          //val similarCount=lines.next().trim.split(":")(1).split("  ").map(t=> t.trim.toLong).to[ListBuffer].drop(1)
          p.similarity=lines.next().trim.split(":")(1).split("  ").map(t=> t.trim).to[ListBuffer].drop(1)
          val catCount=lines.next().trim.split(":")(1).trim.toInt
          for(i<-0 until catCount ){
            p.categories += lines.next().trim
          }
          val reviewMeta=lines.next().trim.split("  ")
          p.reviewsCount=reviewMeta(0).split(":")(2).trim.toInt
          p.downloaded=reviewMeta(1).split(":")(1).trim.toInt
          p.avgRating=reviewMeta(2).split(":")(1).trim.toDouble
          //println("review count is::"+p.reviewsCount)
          var reviewLine=""
          //while (lines. )
            //lines.
          var count=p.reviewsCount
          while(count>0) {
            count = count - 1
            //}

            //for(i<-0 until p.reviewsCount ) {
            val review = lines.next().trim.split(":")

            if (review.size>1) {
            //println(" review are::" + review.mkString(","))
            val r = new Review
            if (review.size > 1) {
              r.Time = review(0).split(" ")(0).trim
              r.customer = review(1).trim.split(" ")(0).trim
              r.ratings = review(2).trim.split(" ")(0).trim.toInt
              r.votes = review(3).trim.split(" ")(0).trim.toInt //.split(":")(1).trim.toInt
              r.helpFul = review(4).trim.toInt //.split(":")(1).trim.toInt
              p.reviews += r
            }
          }
            else{
              count=0
              p=new Product()
            }
          }
          }


      }
    }

    lines.foreach{l=>
      if(l.trim!=""){
        if(l.contains("ID")){
          l.split(":").map(t=> ())
        }
      }else{
        //create new object
      }
      //product


    }


  }

}
