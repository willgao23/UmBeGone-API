package com.example.api.recording;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<AnalyzedRecording>> addRecording(@RequestParam MultipartFile[] files) throws IOException {
        List<AnalyzedRecording> analyzedRecordings = new ArrayList<AnalyzedRecording>();
        ArrayList<RecordingThread> recordingThreads = new ArrayList<RecordingThread>();

        for (MultipartFile file : files) {
            InputStream recording = file.getInputStream();
            RecordingThread recordingThread = new RecordingThread(recording);
            recordingThread.start();
            recordingThreads.add(recordingThread);
        }

        for(RecordingThread thread : recordingThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(RecordingThread thread : recordingThreads) {
            recordingService.addRecording(thread.getRecording());
            analyzedRecordings.add(thread.getRecording());
        }

        return ResponseEntity.ok(analyzedRecordings);
    }

    @DeleteMapping (path = "/{recordingId}")
    public void deleteRecording(@PathVariable("recordingId") Long recordingId) {
        recordingService.deleteRecording(recordingId);
    }
}
