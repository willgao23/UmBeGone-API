package com.example.api.recording;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping (path = "recording-api")
public class RecordingController {

    private RecordingService recordingService;

    @Autowired
    public void RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @GetMapping (path = "/{recordingId}")
    public AnalyzedRecording getAnalyzedRecording(@PathVariable("recordingId") Long recordingId) {
        return recordingService.getAnalyzedRecording(recordingId);
    }

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnalyzedRecording> addRecording(@RequestParam MultipartFile file) throws IOException {
        InputStream recording = file.getInputStream();
        AnalyzedRecording analyzedRecording = new AnalyzedRecording(recording);
        recordingService.addRecording(analyzedRecording);

        return ResponseEntity.ok(analyzedRecording);
    }

    @DeleteMapping (path = "/{recordingId}")
    public void deleteRecording(@PathVariable("recordingId") Long recordingId) {
        recordingService.deleteRecording(recordingId);
    }
}
