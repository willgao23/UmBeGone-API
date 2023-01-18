package com.example.api.recording;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordingRepository extends JpaRepository<AnalyzedRecording, Long> {
    Optional<AnalyzedRecording> findRecordingById(Long recordingId);
}
