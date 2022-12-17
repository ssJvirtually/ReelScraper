package com.example.ReelScraper;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.logging.Level;

public class Test {

    public static void main(String[] args) throws URISyntaxException {

        // get count of available cores
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println(coreCount);

        System.out.println(getDomainName("https://pin.it/136j3ji"));

        System.out.println(removeCharBeforeUrl("Look at this... \uD83D\uDC40\n" +
                "https://pin.it/179erXd"));

//
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable( LogType.PERFORMANCE, Level.ALL );
//        ChromeOptions options = new ChromeOptions();
//        options.setCapability( "goog:loggingPrefs", logPrefs );
//        DevTools devTools = driver.getDevTools();
//        devTools.createSession();
//        devTools.send(Network.enable(
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty()));
//        devTools.addListener(Network.requestWillBeSent(),
//                request -> {
//                    System.out.println(" Request URL : " + request.getRequest().getUrl());
//                });
//
//        driver.get("https://www.instagram.com/reel/CiIPoqYLbex/");
    }

    /**
     * This method returns the domain of the link
     * @param url
     * @return
     * @throws URISyntaxException
     */
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }


    public static String removeCharBeforeUrl(String link){
        return link.replaceAll("[\r\n]+", " ")
                .replaceAll(".*(?=https://)", "");
    }
}