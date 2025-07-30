package com.Rachit.mental_health.service;

import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContentPopulationService {

    @Autowired
    private YouTubeApiService youtubeApiService;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (contentRepository.count() == 0) {
            populateContent();
        }
    }

    public void populateContent() {
        User user = userRepository.findByEmail("rachit181004@gmail.com")
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername("root");
                    newUser.setPassword(passwordEncoder.encode("rootpassword@123")); // Set a secure password in production
                    newUser.setRole("ADMIN");
                    newUser.setEmail("rachit181004@gmail.com");
                    newUser.setCreateDate(LocalDate.now());
                    return userRepository.save(newUser);
                });
        Map<String, String> videoIdToMoodCategory = new HashMap<>();
        videoIdToMoodCategory.put("dQw4w9WgXcQ", "anxious");
        videoIdToMoodCategory.put("eY52Zsg-KVI", "depressed");
        videoIdToMoodCategory.put("5qap5aO4i9A", "relaxed");
        videoIdToMoodCategory.put("ZbZSe6N_BXs", "happy");
        videoIdToMoodCategory.put("JGwWNGJdvx8", "romantic");
        videoIdToMoodCategory.put("fLexgOxsZu0", "motivated");
        videoIdToMoodCategory.put("LHCob76kigA", "calm");
        videoIdToMoodCategory.put("RgKAFK5djSk", "depressed");
        videoIdToMoodCategory.put("kXYiU_JCYtU", "angry");
        videoIdToMoodCategory.put("y6Sxv-sUYtM", "happy");
        videoIdToMoodCategory.put("VYOjWnS4cMY", "energetic");
        videoIdToMoodCategory.put("3JZ_D3ELwOQ", "excited");
        videoIdToMoodCategory.put("e-ORhEE9VVg", "romantic");
        videoIdToMoodCategory.put("60ItHLz5WEA", "motivated");
        videoIdToMoodCategory.put("hT_nvWreIhg", "sad");
        videoIdToMoodCategory.put("fJ9rUzIMcZQ", "nostalgic");
        videoIdToMoodCategory.put("sc4HXjdAqCo", "anxious");
        videoIdToMoodCategory.put("2Vv-BfVoq4g", "romantic");
        videoIdToMoodCategory.put("KyJpvukoK0M", "energetic");
        videoIdToMoodCategory.put("yPYZpwSpKmA", "happy");
        videoIdToMoodCategory.put("vTIIMJ9tUc8", "motivated");
        videoIdToMoodCategory.put("9bZkp7q19f0", "excited");
        videoIdToMoodCategory.put("ub747pprmJ8", "nostalgic");
        videoIdToMoodCategory.put("04854XqcfCY", "happy");
        videoIdToMoodCategory.put("eVTXPUF4Oz4", "angry");
        videoIdToMoodCategory.put("gGdGFtwCNBE", "relaxed");
        videoIdToMoodCategory.put("YQHsXMglC9A", "sad");
        videoIdToMoodCategory.put("uelHwf8o7_U", "calm");
        videoIdToMoodCategory.put("UceaB4D0jpo", "depressed");
        videoIdToMoodCategory.put("b5uLw1PmDdo", "relaxed");
        videoIdToMoodCategory.put("Pw-0pbY9JeU", "sad");
        videoIdToMoodCategory.put("09R8_2nJtjg", "happy");
        videoIdToMoodCategory.put("GMkmQlfOJDk", "calm");
        videoIdToMoodCategory.put("3tmd-ClpJxA", "energetic");
        videoIdToMoodCategory.put("HgzGwKwLmgM", "excited");
        videoIdToMoodCategory.put("2vjPBrBU-TM", "relaxed");
        videoIdToMoodCategory.put("LsoLEjrDogU", "romantic");
        videoIdToMoodCategory.put("CGyEd0aKWZE", "motivated");
        videoIdToMoodCategory.put("YlUKcNNmywk", "angry");
        videoIdToMoodCategory.put("Wch3gJG2GJ4", "depressed");
        videoIdToMoodCategory.put("JBmX6It2gP4", "anxious");
        videoIdToMoodCategory.put("KQ6zr6kCPj8", "happy");
        videoIdToMoodCategory.put("nfWlot6h_JM", "calm");
        videoIdToMoodCategory.put("aJOTlE1K90k", "motivated");
        videoIdToMoodCategory.put("eM3T43AoG8I", "happy");
        videoIdToMoodCategory.put("V-_O7nl0Ii0", "romantic");
        videoIdToMoodCategory.put("9E6b3swbnWg", "sad");
        videoIdToMoodCategory.put("O-zpOMYRi0w", "relaxed");
        videoIdToMoodCategory.put("erG5rgNYSdk", "angry");
        videoIdToMoodCategory.put("IZGwW0RQ8yg", "nostalgic");
        videoIdToMoodCategory.put("8UVNT4wvIGY", "happy");
        videoIdToMoodCategory.put("4NRXx6U8ABQ", "depressed");
        videoIdToMoodCategory.put("09lBLa4j7xc", "sad");
        videoIdToMoodCategory.put("ktvTqknDobU", "happy");
        videoIdToMoodCategory.put("fRh_vgS2dFE", "energetic");
        videoIdToMoodCategory.put("eh7lp9umG2I", "relaxed");
        videoIdToMoodCategory.put("kfVsfOSbJY0", "happy");
        videoIdToMoodCategory.put("7wtfhZwyrcc", "motivated");
        videoIdToMoodCategory.put("nSDgHBxUbVQ", "angry");
        videoIdToMoodCategory.put("k501WJgx37A", "excited");
        videoIdToMoodCategory.put("CMX2lxsA5Lg", "anxious");
        videoIdToMoodCategory.put("WhkJ8KOWgco", "calm");
        videoIdToMoodCategory.put("YykjpeuMNEk", "motivated");
        videoIdToMoodCategory.put("ZDZhVPsFlzI", "calm");
        videoIdToMoodCategory.put("q6EoRBvdVPQ", "relaxed");
        videoIdToMoodCategory.put("0KSOMA3QBU0", "sad");
        videoIdToMoodCategory.put("6Ejga4kJUts", "romantic");
        videoIdToMoodCategory.put("3AtDnEC4zak", "calm");
        videoIdToMoodCategory.put("Ri8Ry2kb-GY", "depressed");
        videoIdToMoodCategory.put("tpUafxhaGD8", "nostalgic");
        videoIdToMoodCategory.put("6ZfuNTqbHE8", "excited");
        videoIdToMoodCategory.put("SM_LP9d8xY4", "anxious");
        videoIdToMoodCategory.put("OWqkoCU5y3Y", "relaxed");
        videoIdToMoodCategory.put("FlsCjmMhFmw", "motivated");
        videoIdToMoodCategory.put("fZyOZ41r7jg", "happy");
        videoIdToMoodCategory.put("pXRviuL6vMY", "nostalgic");
        videoIdToMoodCategory.put("CevxZvSJLk8", "excited");
        videoIdToMoodCategory.put("W85hmt0tKLc", "calm");
        videoIdToMoodCategory.put("QJO3ROT-A4E", "happy");
        videoIdToMoodCategory.put("otCpCn0l4Wo", "nostalgic");
        videoIdToMoodCategory.put("gj5ibYSz8C0", "anxious");
        videoIdToMoodCategory.put("zKZnR6d_nzI", "depressed");
        videoIdToMoodCategory.put("FZLadzn5i6Q", "happy");
        videoIdToMoodCategory.put("vEyzhPdRFXA", "motivated");
        videoIdToMoodCategory.put("hoctwunl05g", "anxious");
        videoIdToMoodCategory.put("59GM_xjPhco", "playful");
        videoIdToMoodCategory.put("7lRV2BmUqTA", "calm");
        videoIdToMoodCategory.put("ta0CvuSGQgY", "relaxed");
        videoIdToMoodCategory.put("y8NGSRW8m4Y", "calm");
        videoIdToMoodCategory.put("6tozHa4Ix8Y", "creative");
        videoIdToMoodCategory.put("tAGnKpE4NCI", "sad");
        videoIdToMoodCategory.put("EGikhmjTSZI", "depressed");
        videoIdToMoodCategory.put("Uj1ykZWtPYI", "angry");
        videoIdToMoodCategory.put("BzE1mX4Px0I", "excited");
        videoIdToMoodCategory.put("opU1urLhw50", "happy");
        videoIdToMoodCategory.put("6Dh-RL__uN4", "romantic");
        videoIdToMoodCategory.put("ZGdLIwrGHG8", "relaxed");
        videoIdToMoodCategory.put("ZJL4UGSbeFg", "romantic");
        videoIdToMoodCategory.put("lWA2pjMjpBs", "depressed");
        videoIdToMoodCategory.put("J3UjJ4wKLkg", "anxious");
        videoIdToMoodCategory.put("R6S7T8U9V0", "excited");
        videoIdToMoodCategory.put("m1n2o3p4q5R", "calm");
        videoIdToMoodCategory.put("G1H2I3J4K5", "excited");
        videoIdToMoodCategory.put("S1T2U3V4W5", "romantic");
        videoIdToMoodCategory.put("C1D2E3F4G5", "calm");
        videoIdToMoodCategory.put("O1P2Q3R4S5", "sad");
        videoIdToMoodCategory.put("I1J2K3L4M5", "relaxed");
        videoIdToMoodCategory.put("Y6Z7A8B9C0", "happy");
        videoIdToMoodCategory.put("Q1R2S3T4U5", "anxious");
        videoIdToMoodCategory.put("N1O2P3Q4R5", "motivated");
        videoIdToMoodCategory.put("D1E2F3G4H5", "angry");
        videoIdToMoodCategory.put("W6X7Y8Z9A0", "happy");
        videoIdToMoodCategory.put("B6C7D8E9F0", "anxious");
        videoIdToMoodCategory.put("Y1z2A3B4C5", "calm");
        videoIdToMoodCategory.put("L6M7N8O9P0", "romantic");
        videoIdToMoodCategory.put("g6h7i8j9k0L", "nostalgic");
        videoIdToMoodCategory.put("Z6A7B8C9D0", "motivated");
        videoIdToMoodCategory.put("T1U2V3W4X5", "excited");
        videoIdToMoodCategory.put("J1K2L3M4N5", "happy");
        videoIdToMoodCategory.put("s6t7u8v9w0X", "calm");
        videoIdToMoodCategory.put("H6I7J8K9L0", "depressed");
        videoIdToMoodCategory.put("B1C2D3E4F5", "romantic");
        videoIdToMoodCategory.put("Z1A2B3C4D5", "sad");
        videoIdToMoodCategory.put("Q6R7S8T9U0", "relaxed");
        videoIdToMoodCategory.put("a1b2c3d4e5F", "depressed");
        videoIdToMoodCategory.put("V1W2X3Y4Z5", "nostalgic");
        videoIdToMoodCategory.put("M6N7O8P9Q0", "depressed");
        videoIdToMoodCategory.put("C6D7E8F9G0", "energetic");
        videoIdToMoodCategory.put("N6O7P8Q9R0", "relaxed");
        videoIdToMoodCategory.put("W1X2Y3Z4A5", "calm");
        videoIdToMoodCategory.put("D6E7F8G9H0", "excited");
        videoIdToMoodCategory.put("J6K7L8M9N0", "romantic");
        videoIdToMoodCategory.put("G6H7I8J9K0", "nostalgic");
        videoIdToMoodCategory.put("I6J7K8L9M0", "sad");
        videoIdToMoodCategory.put("Y1Z2A3B4C5", "angry");
        videoIdToMoodCategory.put("L1M2N3O4P5", "calm");
        videoIdToMoodCategory.put("M1N2O3P4Q5", "anxious");
        videoIdToMoodCategory.put("KTvTqknDobU", "motivated");
        videoIdToMoodCategory.put("PW-0pbY9JeU", "relaxed");
        videoIdToMoodCategory.put("GJ9Y4GfdgkM", "excited");
        videoIdToMoodCategory.put("kQJmIQfHG_U", "anxious");
        videoIdToMoodCategory.put("egZs4dUXztU", "happy");
        videoIdToMoodCategory.put("E1F2G3H4I5", "calm");
        videoIdToMoodCategory.put("P2P3Q4R5S6", "anxious");
        videoIdToMoodCategory.put("O6P7Q8R9S0", "depressed");
        videoIdToMoodCategory.put("D3E4F5G6H7", "happy");
        videoIdToMoodCategory.put("TpQ9VmN_HOw", "romantic");
        videoIdToMoodCategory.put("sEtq_vZLZ8M", "motivated");
        videoIdToMoodCategory.put("n1ywfCQkRBI", "angry");
        videoIdToMoodCategory.put("IjDA7L3hMH4", "relaxed");
        videoIdToMoodCategory.put("ZFQ_LGbO1bI", "depressed");

        for (Map.Entry<String, String> entry : videoIdToMoodCategory.entrySet()) {
            Content content = youtubeApiService.fetchVideoAsContent(entry.getKey(), entry.getValue());
            if (content == null) {
                // Optionally log: System.out.println("No content for video ID: " + entry.getKey());
                continue;
            }
            content.setUser(user);
            contentRepository.save(content);

        }
    }
}
