package ru.job4j.socialmedia.repository.file;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.File;

public interface FileRepository extends CrudRepository<File, Integer> {
}
