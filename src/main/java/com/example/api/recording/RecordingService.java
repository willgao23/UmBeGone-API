package com.example.api.recording;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordingService {

    private RecordingRepository recordingRepository;

    @Autowired
    public void RecordingService(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;
    }

    public AnalyzedRecording getAnalyzedRecording(Long recordingId) {
        Optional<AnalyzedRecording> recordingOptional = recordingRepository.findRecordingById(recordingId);

        if (!recordingOptional.isPresent()) {
            throw new IllegalStateException("There is no recording with an ID of " + recordingId + "!");
        }

        AnalyzedRecording recording = recordingRepository.getReferenceById(recordingId);
        return recording;
    }

    
}
