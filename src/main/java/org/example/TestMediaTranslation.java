package org.example;

import com.google.api.gax.rpc.BidiStream;
import com.google.cloud.mediatranslation.v1beta1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestMediaTranslation {
    public static void useGRPCTransport(String filePath) throws IOException {
        System.out.println("Calling useGRPCTransport() in TestMediaTranslation");
        try (SpeechTranslationServiceClient speechTranslationServiceClient = SpeechTranslationServiceClient.create()) {
            Path path = Paths.get(filePath);
            byte[] content = Files.readAllBytes(path);
            ByteString audioContent = ByteString.copyFrom(content);

            TranslateSpeechConfig audioConfig =
                    TranslateSpeechConfig.newBuilder()
                            .setAudioEncoding("linear16")
                            .setSampleRateHertz(16000)
                            .setSourceLanguageCode("en-US")
                            .setTargetLanguageCode("fr-FR")
                            .build();

            StreamingTranslateSpeechConfig config =
                    StreamingTranslateSpeechConfig.newBuilder()
                            .setAudioConfig(audioConfig)
                            .setSingleUtterance(true)
                            .build();

            BidiStream<StreamingTranslateSpeechRequest, StreamingTranslateSpeechResponse> bidiStream =
                    speechTranslationServiceClient.streamingTranslateSpeechCallable().call();

            // The first request contains the configuration.
            StreamingTranslateSpeechRequest requestConfig =
                    StreamingTranslateSpeechRequest.newBuilder().setStreamingConfig(config).build();

            // The second request contains the audio
            StreamingTranslateSpeechRequest request =
                    StreamingTranslateSpeechRequest.newBuilder().setAudioContent(audioContent).build();

            bidiStream.send(requestConfig);
            bidiStream.send(request);

            System.out.println("Done useGRPCTransport() in TestMediaTranslation");
        }
    }

    public static void useRESTTransport(String filePath) throws IOException {
        System.out.println("Calling useRESTTransport() in TestMediaTranslation");
        SpeechTranslationServiceSettings speechTranslationServiceSettings = SpeechTranslationServiceSettings.newHttpJsonBuilder().build();
        try (SpeechTranslationServiceClient speechTranslationServiceClient = SpeechTranslationServiceClient.create(speechTranslationServiceSettings)) {
            Path path = Paths.get(filePath);
            byte[] content = Files.readAllBytes(path);
            ByteString audioContent = ByteString.copyFrom(content);

            TranslateSpeechConfig audioConfig =
                    TranslateSpeechConfig.newBuilder()
                            .setAudioEncoding("linear16")
                            .setSampleRateHertz(16000)
                            .setSourceLanguageCode("en-US")
                            .setTargetLanguageCode("fr-FR")
                            .build();

            StreamingTranslateSpeechConfig config =
                    StreamingTranslateSpeechConfig.newBuilder()
                            .setAudioConfig(audioConfig)
                            .setSingleUtterance(true)
                            .build();

            BidiStream<StreamingTranslateSpeechRequest, StreamingTranslateSpeechResponse> bidiStream =
                    speechTranslationServiceClient.streamingTranslateSpeechCallable().call();

            // The first request contains the configuration.
            StreamingTranslateSpeechRequest requestConfig =
                    StreamingTranslateSpeechRequest.newBuilder().setStreamingConfig(config).build();

            // The second request contains the audio
            StreamingTranslateSpeechRequest request =
                    StreamingTranslateSpeechRequest.newBuilder().setAudioContent(audioContent).build();

            bidiStream.send(requestConfig);
            bidiStream.send(request);

            System.out.println("Done useRESTTransport() in TestMediaTranslation");
        }
    }
}
