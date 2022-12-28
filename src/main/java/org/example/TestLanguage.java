package org.example;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;

import java.io.IOException;

public class TestLanguage {
    public static void useGRPCTransport() throws IOException {
        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {
            Document document = Document.newBuilder().setContent("Hello World!").setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
            System.out.println(response.getLanguage());
        }
    }

    public static void useRESTTransport() throws IOException {
        LanguageServiceSettings languageServiceSettings = LanguageServiceSettings.newHttpJsonBuilder().build();
        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create(languageServiceSettings)) {
            Document document = Document.newBuilder().setContent("Hello World!").setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
            System.out.println(response.getLanguage());
        }
    }
}
