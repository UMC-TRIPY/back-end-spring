package com.example.tripy.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.tripy.domain.conversation.Conversation;
import com.example.tripy.domain.conversation.ConversationRepository;
import com.example.tripy.domain.conversation.dto.ConversationRequestDto.ConversationCreateRequest;
import com.example.tripy.domain.country.Country;
import com.example.tripy.domain.country.CountryRepository;
import com.example.tripy.domain.language.LanguageRepository;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import com.example.tripy.global.s3.dto.S3Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
@Service
public class S3Service {

    private final Integer KOREAN_INDEX = 0;
    private final Integer ENGLISH_INDEX = 1;
    private final Integer JAPANESE_INDEX = 2;
    private final Integer SPANISH_INDEX = 3;
    private final Integer FRENCH_INDEX = 4;
    private final String ENGLISH = "english";
    private final String JAPANESE = "japanese";
    private final String SPANISH = "spanish";
    private final String FRENCH = "french";
    private final String CONVERSATION_FILE_NAME = "conversation.csv";
    private final Integer SELECT_ROW_NUMBER = 3;

    private final AmazonS3Client amazonS3Client;
    private final ConversationRepository conversationRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public List<S3Result> uploadFiles(List<MultipartFile> multipartFiles) {
        List<S3Result> fileList = new ArrayList<>();

        // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileList에 추가
        multipartFiles.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(
                    new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "파일 업로드에 실패했습니다.");
            }
            fileList.add(new S3Result(amazonS3Client.getUrl(bucket, fileName).toString()));
        });
        return fileList;
    }

    public S3Result uploadFile(MultipartFile multipartFile) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }
        return new S3Result(amazonS3Client.getUrl(bucket, fileName).toString());
    }

    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }


    @Scheduled(cron = "0 0 2 * * *")
    public void getConversation() {
        String fileName = CONVERSATION_FILE_NAME;

        try {
            conversationRepository.deleteAll();
            S3Object s3Object = amazonS3Client.getObject(bucket, fileName);

            // 파일 내용을 읽어오기 위해 BufferedReader 사용
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(s3Object.getObjectContent()));

            // 한 줄씩 읽어와서 출력
            List<String> lines = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            List<String> selectedLines = selectRandomLines(lines, SELECT_ROW_NUMBER);

            for (int i = 0; i < SELECT_ROW_NUMBER; i++) {
                String row = selectedLines.get(i);
                List<String> tokens = Arrays.asList(row.split(","));

                String korean = tokens.get(KOREAN_INDEX);
                String english = tokens.get(ENGLISH_INDEX);
                String japanese = tokens.get(JAPANESE_INDEX);
                String spanish = tokens.get(SPANISH_INDEX);
                String french = tokens.get(FRENCH_INDEX);

                saveConversation(korean, english, countryRepository.findByLanguage(
                    languageRepository.findByLanguageName(ENGLISH)));

                saveConversation(korean, japanese, countryRepository.findByLanguage(
                    languageRepository.findByLanguageName(JAPANESE)));

                saveConversation(korean, spanish, countryRepository.findByLanguage(
                    languageRepository.findByLanguageName(SPANISH)));

                saveConversation(korean, french, countryRepository.findByLanguage(
                    languageRepository.findByLanguageName(FRENCH)));
            }

            // BufferedReader 닫기
            reader.close();
        } catch (IOException e) {
            throw new GeneralException(ErrorStatus._FAULT_S3_KEY);
        }
    }

    private List<String> selectRandomLines(List<String> lines, int count) {
        List<String> selectedLines = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(lines.size());
            selectedLines.add(lines.get(index));
        }

        return selectedLines;
    }

    private void saveConversation(String korean, String translationAndPronunciation,
        Country country) {
        String foreignLanguage;
        String pronunciation;

        // 괄호를 기준으로 영어 부분과 한국어 부분 파싱
        int openBracketIndex = translationAndPronunciation.indexOf("(");
        int closeBracketIndex = translationAndPronunciation.indexOf(")");

        foreignLanguage = translationAndPronunciation.substring(0, openBracketIndex).trim();
        pronunciation = translationAndPronunciation.substring(openBracketIndex + 1,
            closeBracketIndex).trim();

        conversationRepository.save(Conversation.toEntity(
            new ConversationCreateRequest(korean, foreignLanguage, pronunciation, country)
        ));

    public String parseFileName(String imgURl) {
        String[] st = imgURl.split("/");
        return st[st.length - 1];
    }
}