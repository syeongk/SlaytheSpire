package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ui.GameState;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GameSave {
    public static void main(String[] args) throws JsonProcessingException {
        // JSON 읽기
        readJsonFile();

        // JSON 쓰기
        writeJsonFile();
    }

    // JSON 읽기
    public static void readJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON 파일에서 데이터를 읽어 Map으로 변환
            File file = new File("new_game_save.json");
            Map<String, Object> data = mapper.readValue(file, Map.class);

            // 읽은 데이터 출력
            System.out.println("JSON 데이터:");
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON 쓰기
    public static void writeJsonFile() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        GameState gameState = GameState.getInstance();
        String jsonString = mapper.writeValueAsString(gameState);
        System.out.println(jsonString);
        try {
            // 데이터를 생성
            Map<String, Object> data = Map.of(
                    "userId", "56789101112",
                    "currentLevel", 10,
                    "score", 3000,
                    "inventory", new String[] {"bow", "arrow", "elixir"},
                    "position", Map.of("x", 100, "y", 200),
                    "health", 85
            );

            // 데이터를 JSON 파일로 저장
            File file = new File("new_game_save.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);

            System.out.println("JSON 파일에 저장 완료: new_game_save.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
