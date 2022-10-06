package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    private Integer id;

    @NotNull(message = "Word field is required")
    @NotEmpty(message = "Word field is required")
    private String word;

    public Tag() {
    }

    public Tag(Integer id, String word) {
        this.id = id;
        this.word = word;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
