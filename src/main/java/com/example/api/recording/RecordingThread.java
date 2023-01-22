package com.example.api.recording;

import java.io.IOException;
import java.io.InputStream;

public class RecordingThread extends Thread {
    private InputStream file;
    private AnalyzedRecording recording;

    public RecordingThread(InputStream file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            this.recording = new AnalyzedRecording(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AnalyzedRecording getRecording() {
        return this.recording;
    }
}
