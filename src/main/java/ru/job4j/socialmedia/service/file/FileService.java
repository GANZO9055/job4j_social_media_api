package ru.job4j.socialmedia.service.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.File;
import ru.job4j.socialmedia.repository.file.FileRepository;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public void createFile(File file) {
        fileRepository.save(file);
    }

    @Transactional
    public void deleteFileById(Integer id) {
        fileRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllFile() {
        fileRepository.deleteAll();
    }
}
