package com.porsche.tests;
import com.porsche.utilities.Driver;
import com.porsche.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import static com.porsche.utilities.BrowserUtils.open;
import static com.porsche.utilities.BrowserUtils.priceStringToInt;

public class testPorsche extends TestBase {

    @Test
    public void porscheTests() {
        extentLogger = report.createTest("porscheTests");
        extentLogger.info("all tests are here, login now");
        open();
        extentLogger.info("click on the link model 718");
        pages.start().link718.click();
//       step 4  price of Cayman S 718
        String basePriceCaymanS = pages.m718().priceBoxCaymanS.getText();
        System.out.println(basePriceCaymanS);
//        System.out.println(basePriceCaymanS);
//        we have to get rid of $ and * and text
        basePriceCaymanS = basePriceCaymanS.substring(basePriceCaymanS.indexOf('$')+2,basePriceCaymanS.indexOf("*")).trim().replace(".","");
        String priceBase = basePriceCaymanS.substring(0,basePriceCaymanS.length()-2);
//        System.out.println(priceBase);
//        Click on 718 Cayman S
        extentLogger.info("click on the link model 718 CaymanS");
        pages.m718().link718CaymanS.click();
//        Verify that Base price displayed on the page is same as the price from step 4
        Driver.getDriver().getWindowHandles();


        Object[] windows = Driver.getDriver().getWindowHandles().toArray();
        String windowsID1= windows [0].toString();// main window
        String windowsID2= windows [1].toString();// pop up window
        System.out.println(windowsID2);

//        since there is a new window opened
        Driver.getDriver().switchTo().window(windowsID2);
        String basePriceCaymanS2 = pages.m718CS().basePriceBox.getText().substring(1); // $ sign removed
//        System.out.println(basePriceCaymanS);
        extentLogger.info("verifying the base price on this page and the previous page");
        softAssert.assertTrue(priceBase.equals(basePriceCaymanS2),"prices are not same");
//        7. Verify that Price for Equipment is 0
        String actualEqPrice = pages.m718CS().equipmentPriceBox.getText().substring(1);
        String expectedEqPrice = "0";
        extentLogger.info("verifying that the base price is zero");
        softAssert.assertTrue(actualEqPrice.equals(expectedEqPrice),"equipment price is wrong");
//        8. Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
        int deliveryPrice = priceStringToInt(pages.m718CS().deliveryPriceBox);
//        int deliveryPrice = Integer.valueOf(m718CS.deliveryPriceBox.getText().substring(1).replace(",",""));
//        int equipmentPrice = Integer.valueOf(actualEqPrice);
        int equipmentPrice =priceStringToInt(pages.m718CS().equipmentPriceBox);
//        int actualBasePrice = Integer.valueOf(basePriceCaymanS2.replace(",",""));
        int actualBasePrice =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPrice = priceStringToInt(pages.m718CS().totalPriceBox);
        extentLogger.info("verifying that total price is the sum of base price + Delivery, Processing and Handling Fee");
        softAssert.assertTrue(totalPrice==actualBasePrice+equipmentPrice+deliveryPrice,"total price is wrong");
//        System.out.println(totalPrice);
//        9. Select color “Miami Blue”
        extentLogger.info("Select color “Miami Blue");
        pages.m718CS().colorMiami.click();
//      10. verify that  price for equipment for Miami Blue" is equal to "price for equipment for standard color"
//        the value will change from 0 to 2580  be careful
        wait.until(ExpectedConditions.textToBe(By.xpath("(//div[@class='ccaPrice'])[6]"),"$2,580"));
//        int equipmentPriceMiami = Integer.valueOf(m718CS.equipmentPriceBox.getText().substring(1).replace(",",""));
        int equipmentPriceMiami =priceStringToInt(pages.m718CS().equipmentPriceBox); // price on the web table
        System.out.println(equipmentPriceMiami);
        System.out.println(pages.m718CS().priceBoxMiamiColor.getAttribute("data-price").replace(",","").substring(1));
        extentLogger.info("verifying that price for equipment for Miami Blue is equal to price for equipment for standard color");
        softAssert.assertTrue(Integer.valueOf(pages.m718CS().priceBoxMiamiColor.getAttribute("data-price").replace(",","").substring(1))==equipmentPriceMiami,"equipment price of Miami Blue is not same as standard colors");
//        11.Verify that total price is the sum of base price + Price for Equipment + Delivery,Processing and Handling Fee
        int deliveryPriceMiami = priceStringToInt(pages.m718CS().deliveryPriceBox);
        int actualBasePriceMiami =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPriceMiami = priceStringToInt(pages.m718CS().totalPriceBox);
        extentLogger.info("verifying total price is the sum of base price + Price for Equipment + Delivery,Processing and Handling Fee");
        softAssert.assertTrue(totalPriceMiami==actualBasePriceMiami+equipmentPriceMiami+deliveryPriceMiami,"total price of Miami is wrong");
        System.out.println(totalPriceMiami);
//        12.Select 20" Carrera Sport Wheels
        extentLogger.info("Select Wheels");
        pages.m718CS().carreraWheel.click();
//        price of wheels
        System.out.println(priceStringToInt(pages.m718CS().selectedWheelPrice));
//        13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
        int equipmentPriceMiamiWithCarrera =priceStringToInt(pages.m718CS().equipmentPriceBox);
        int priceOfCarreraWheels = priceStringToInt(pages.m718CS().selectedWheelPrice);
        extentLogger.info(" verifying Price for Equipment is the sum of Miami Blue price + 20' Carrera Sport Wheels");
        softAssert.assertTrue(equipmentPriceMiamiWithCarrera==equipmentPriceMiami+priceOfCarreraWheels);
//        14.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        int deliveryPriceMiamiCar = priceStringToInt(pages.m718CS().deliveryPriceBox);
        int actualBasePriceMiamiCar =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPriceMiamiCar = priceStringToInt(pages.m718CS().totalPriceBox);
        int equipmentPriceMiamiCar = priceStringToInt(pages.m718CS().equipmentPriceBox);
        extentLogger.info(" verifying total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
        softAssert.assertTrue(totalPriceMiamiCar==deliveryPriceMiamiCar+actualBasePriceMiamiCar+equipmentPriceMiamiCar);
//        15.Select seats ‘Power Sport Seats (14 way) with Memory Package’
//        scroll down on the page
        extentLogger.info("scrolling down");
        actions.moveToElement(pages.m718CS().options).perform();
//        wait.until(ExpectedConditions.elementToBeClickable(m718CS.powerSSeat));
        extentLogger.info("Selecting Power Sport Seats (14 way) with Memory Package");
        pages.m718CS().powerSSeat.click();
//        16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels+ Power Sport Seats (14way) with Memory Package
        int pricePowerSeat =priceStringToInt(pages.m718CS().pricePowerSeatBox);
        System.out.println(pricePowerSeat);
        int equipmentPriceMiamiCarPSeat = priceStringToInt(pages.m718CS().equipmentPriceBox); // equipment price with wheels+power seats
        System.out.println(equipmentPriceMiamiCar+pricePowerSeat); // price of Miami Blue price + 20" Carrera Sport Wheels+ Power Sport Seats (14way) with Memory Package
        extentLogger.info(" verifying Price for Equipment is the sum of Miami Blue price + 20' Carrera Sport Wheels+ Power Sport Seats (14way) with Memory Package");
        softAssert.assertTrue( equipmentPriceMiamiCarPSeat==equipmentPriceMiamiCar+pricePowerSeat);
//        17.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        int deliveryPriceMiamiCarPS = priceStringToInt(pages.m718CS().deliveryPriceBox);
        int actualBasePriceMiamiCarPS =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPriceMiamiCarPS = priceStringToInt(pages.m718CS().totalPriceBox);
        extentLogger.info(" verifying total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
        softAssert.assertTrue(totalPriceMiamiCarPS==deliveryPriceMiamiCarPS+actualBasePriceMiamiCarPS+equipmentPriceMiamiCarPSeat);
//        18.Click on Interior Carbon Fiber
        extentLogger.info("clicking on interior carbon fiber");
        actions.moveToElement(pages.m718CS().delExperience).perform();
        pages.m718CS().linkICF.click();
//        19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
        extentLogger.info("Selecting Interior Trim in Carbon Fiber i.c.w. Standard Interior");
        pages.m718CS().interiorTrimStandardCheckBox.click();
//        20.Verify that Price for Equipment is the sum of Miami Blue price + 20Carrera SportWheels+ Power Sport Seats (14way) with Memory Package + Interior Trim inCarbon Fiber i.c.w.Standard Interior
        int priceIntTrStandard = priceStringToInt(pages.m718CS().priceInteriorTrimstandard);
        int equipmentPriceMiamiCarPSeatInterior = priceStringToInt(pages.m718CS().equipmentPriceBox); // equipment price with wheels+power seats+interiorTrim
        System.out.println(equipmentPriceMiami+priceOfCarreraWheels+pricePowerSeat+priceIntTrStandard);
        System.out.println(equipmentPriceMiamiCarPSeatInterior);
        extentLogger.info(" verifying Price for Equipment is the sum of Miami Blue price + 20Carrera SportWheels+ Power Sport Seats (14way) with Memory Package + Interior Trim inCarbon Fiber i.c.w.Standard Interior");
        softAssert.assertTrue(equipmentPriceMiamiCarPSeatInterior==equipmentPriceMiami+priceOfCarreraWheels+pricePowerSeat+priceIntTrStandard);
//       21.  Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        int deliveryPriceMiamiCarPSInt = priceStringToInt(pages.m718CS().deliveryPriceBox);
        int actualBasePriceMiamiCarPSInt =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPriceMiamiCarPSInt = priceStringToInt(pages.m718CS().totalPriceBox);
        extentLogger.info("verifying total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
        softAssert.assertTrue(totalPriceMiamiCarPSInt==deliveryPriceMiamiCarPSInt+actualBasePriceMiamiCarPSInt+equipmentPriceMiamiCarPSeatInterior);
//        22.Click on Performance
        extentLogger.info("clicking on the performans");
        pages.m718CS().linkPerformance.click();
//        23.Select 7 speed Porsche Doppelkupplung (PDK)
        extentLogger.info("selecting PDK");
        pages.m718CS().speed7RadioButton.click();
        extentLogger.info("selecting  Ceramic Composite Brakes (PCCB) ");
//        24.Select Porsche Ceramic Composite Brakes (PCCB)
        pages.m718CS().PCCB.click();
//        25. Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera SportWheels+ Power Sport Seats
//        (14way) with Memory Package +Interior Trim in Carbon Fiber i.c.w.Standard Interior + 7speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
        int priceOf7Speed = priceStringToInt(pages.m718CS().price7Speed);
        int priceOfPCCB = priceStringToInt(pages.m718CS().pricePCCB);
        int equipmentPriceMiamiCarPSeatInterior7SpeedPCCB = priceStringToInt(pages.m718CS().equipmentPriceBox); // equipment price with wheels+power seats+interiorTrim++ 7speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
        extentLogger.info("verifying Price for Equipment is the sum of Miami Blue price + 20' Carrera SportWheels+ Power Sport Seats" +"" +
                "(14way) with Memory Package +Interior Trim in Carbon Fiber i.c.w.Standard Interior + 7speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)");
        softAssert.assertTrue(equipmentPriceMiamiCarPSeatInterior7SpeedPCCB==equipmentPriceMiami+priceOfCarreraWheels+pricePowerSeat+priceIntTrStandard+priceOf7Speed+priceOfPCCB);
//        26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        int deliveryFinal = priceStringToInt(pages.m718CS().deliveryPriceBox);
        int actualBasePriceFinal =priceStringToInt(pages.m718CS().basePriceBox);
        int totalPriceFinal = priceStringToInt(pages.m718CS().totalPriceBox);
        int equipmentPriceFinal = priceStringToInt(pages.m718CS().equipmentPriceBox);
        extentLogger.info("verifying  total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee ");
        softAssert.assertTrue(totalPriceFinal==deliveryFinal+actualBasePriceFinal+equipmentPriceFinal);
        extentLogger.pass("end of Porsche test");
    }
}
