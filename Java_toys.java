import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

class Toy {
    int id;
    String name;
    int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }
}

public class Java_toys {
    private PriorityQueue<Toy> toyQueue;
    private Random random;

    public Java_toys() {
        toyQueue = new PriorityQueue<>((t1, t2) -> t2.frequency - t1.frequency);
        random = new Random();
    }

    public void put(String input) {
        String[] parts = input.split(" ");
        if (parts.length >= 3) {
            try {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int frequency;
                try {
                    frequency = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    // Если не удается распарсить частоту, пропускаем строку
                    System.err.println("Ошибка парсинга частоты в строке: " + input);
                    return;
                }
                Toy toy = new Toy(id, name, frequency);
                toyQueue.add(toy);
            } catch (NumberFormatException e) {
                // Обрабатываем ошибку парсинга и игнорируем строку
                System.err.println("Ошибка парсинга строки: " + input);
            }
        } else {
            // Если строки не содержат минимум трех частей, игнорируем их
            System.err.println("Некорректный формат строки: " + input);
        }
    }
    

    public int get() {
        int randValue = random.nextInt(100) + 1;

        if (randValue <= 20) {
            return 1;
        } else if (randValue <= 40) {
            return 2;
        } else {
            return 3;
        }
    }

    public void generateResultsToFile(String filename, int numIterations) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < numIterations; i++) {
                int toyId = get();
                writer.write("Get result: " + toyId + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Java_toys toyQueue = new Java_toys();
        toyQueue.put("1 2 конструктор");
        toyQueue.put("2 2 робот");
        toyQueue.put("3 6 кукла");

        toyQueue.generateResultsToFile("output.txt", 10);
    }
}
