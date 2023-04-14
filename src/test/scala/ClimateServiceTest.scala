import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  // Create a test.
  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
    assert(ClimateService.isClimateRelated("DCF") == false)
    assert(ClimateService.isClimateRelated("global warming morning") == true)
  }

  //@TODO
  test("parseRawData") {
    // our inputs January
    val firstRecord = (2003, 1, 355.2) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val thirdRecord = (2005, 1, 378.48)
    val list1 = List(firstRecord, secondRecord, thirdRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType1 = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val co2RecordWithType3 = CO2Record(thirdRecord._1, thirdRecord._2, thirdRecord._3)
    val output1 = List(Some(co2RecordWithType1), Some(co2RecordWithType2), Some(co2RecordWithType3))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output1)

    // our inputs February
    val firstRecord2 = (2003, 2, 375.4)
    val secondRecord2 = (2004, 2, 377.53)
    val thirdRecord2 = (2005, 2, 379.37)
    val list2 = List(firstRecord2, secondRecord2, thirdRecord2)

    // our output of our method "parseRawData"
    val co2RecordWithType21 = CO2Record(firstRecord2._1, firstRecord2._2, firstRecord2._3)
    val co2RecordWithType22 = CO2Record(secondRecord2._1, secondRecord2._2, secondRecord2._3)
    val co2RecordWithType23 = CO2Record(thirdRecord2._1, thirdRecord2._2, thirdRecord2._3)
    val output2 = List(Some(co2RecordWithType21), Some(co2RecordWithType22), Some(co2RecordWithType23))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list2) == output2)
  }

  // Find the minimum and maximum values.
  test("Min - Max") {
    val firstRecord2 = (2003, 2, 375.4)
    val secondRecord2 = (2004, 2, 377.53)
    val thirdRecord2 = (2005, 2, 379.37)
    val list2 = List(firstRecord2, secondRecord2, thirdRecord2)

    val co2RecordWithType21 = CO2Record(firstRecord2._1, firstRecord2._2, firstRecord2._3)
    val co2RecordWithType22 = CO2Record(secondRecord2._1, secondRecord2._2, secondRecord2._3)
    val co2RecordWithType23 = CO2Record(thirdRecord2._1, thirdRecord2._2, thirdRecord2._3)
    val input1 = List(co2RecordWithType21, co2RecordWithType22, co2RecordWithType23)

    assert(ClimateService.getMinMax(input1) == (375.4, 379.37))
  }

  // Find the minimum and maximum values by year.
  test("Min - Max by year") {
    val firstRecord = (2003, 1, 355.2)
    val secondRecord = (2004, 1, 375.2)
    val thirdRecord = (2005, 1, 378.48)
    val firstRecord2 = (2003, 2, 375.4)
    val secondRecord2 = (2004, 2, 377.53)
    val thirdRecord2 = (2005, 2, 379.37)
    val list2 = List(firstRecord2, secondRecord2, thirdRecord2)

    val co2RecordWithType1 = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val co2RecordWithType3 = CO2Record(thirdRecord._1, thirdRecord._2, thirdRecord._3)
    val co2RecordWithType21 = CO2Record(firstRecord2._1, firstRecord2._2, firstRecord2._3)
    val co2RecordWithType22 = CO2Record(secondRecord2._1, secondRecord2._2, secondRecord2._3)
    val co2RecordWithType23 = CO2Record(thirdRecord2._1, thirdRecord2._2, thirdRecord2._3)
    val input1 = List(co2RecordWithType1, co2RecordWithType2, co2RecordWithType3, co2RecordWithType21, co2RecordWithType22, co2RecordWithType23)

    assert(ClimateService.getMinMaxByYear(input1, 2003) == (355.2, 375.4))
    assert(ClimateService.getMinMaxByYear(input1, 2004) == (375.2, 377.53))
  }

  test("Min max difference") {
    val min1 = 3
    val max1 = 67
    val min2 = 0.7389
    val max2 = 88.65
    val dif2 = max2-min2

    assert(ClimateService.getDifference(List(min1, max1)) == (64))
    assert(ClimateService.getDifference(List(min2, max2)) == (dif2))
  }


  //@TODO
  test("filterDecemberData") {
    val firstRecord = (1990, 12, 353.89)
    val secondRecord = (1995, 3, 361.62)
    val thirdRecord = (2004, 12, 377.33)
    val co2RecordWithType1 = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val co2RecordWithType3 = CO2Record(thirdRecord._1, thirdRecord._2, thirdRecord._3)
    val input1 = List(Some(co2RecordWithType1), Some(co2RecordWithType2), Some(co2RecordWithType3))
    assert(ClimateService.filterDecemberData(input1) == List(co2RecordWithType2))

    val input2 = List(Some(co2RecordWithType1), Some(co2RecordWithType3))
    assert(ClimateService.filterDecemberData(input2) == List())
  }
}
