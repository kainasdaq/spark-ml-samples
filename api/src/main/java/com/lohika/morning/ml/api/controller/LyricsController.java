package com.lohika.morning.ml.api.controller;

import com.lohika.morning.ml.api.service.Genre;
import com.lohika.morning.ml.api.service.LyricsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lyrics")
public class LyricsController {

    @Autowired
    private LyricsService lyricsService;

    @RequestMapping(value = "/train", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> trainLyricsModel() {
        Map<String, Object> trainStatistics = lyricsService.classifyLyrics();

        return new ResponseEntity<>(trainStatistics, HttpStatus.OK);
    }

    @RequestMapping(value = "/predict", method = RequestMethod.POST)
    ResponseEntity<String> predictGenre(@RequestBody String unknownLyrics) {
        Genre genre = lyricsService.predictGenre(unknownLyrics);

        return new ResponseEntity<>(genre.getValue(), HttpStatus.OK);
    }

}