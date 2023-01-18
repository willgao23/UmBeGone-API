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

    public void addRecording(AnalyzedRecording recording) {
        Optional<AnalyzedRecording> recordingOptional = recordingRepository.findRecordingById(recording.getId());

        if(recordingOptional.isPresent()) {
            throw new IllegalStateException("A recording already exists with an ID of " + recording.getId() + "!");
        }

        recordingRepository.save(recording);
    }

    public void deleteRecording(Long recordingId) {
        Optional<AnalyzedRecording> recordingOptional = recordingRepository.findById(recordingId);

        if (!recordingOptional.isPresent()) {
            throw new IllegalStateException("There is no recording with an ID of " + recordingId + "!");
        }

        recordingRepository.delete(recordingOptional.get());
    }
}
