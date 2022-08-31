package com.example.ReelScraper.Controller;


import com.example.ReelScraper.Service.GetReel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@CrossOrigin
public class DownloadReel {

    @Autowired
    GetReel getReel;


    @GetMapping("downloadReel")
    public ResponseEntity<byte[]> downloadReels(@RequestParam String link) throws IOException {

        System.out.println(link);
      File file = getReel.getReels(link)  ;
      if(file.isFile()){
          byte[] videoResource = method(file);
          return ResponseEntity.ok()
                  .contentType(MediaType.parseMediaType("video/mp4"))
                  .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=video_%s.%s", 1, "mp4"))
                  .body(videoResource);
      }

      return null;
    }

    public static byte[] method(File file)
            throws IOException, FileNotFoundException {

        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(file);

        // Now creating byte array of same length as file
        byte[] arr = new byte[(int)file.length()];

        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);

        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();

        // Returning above byte array
        return arr;
    }

}
