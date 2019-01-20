package ru.zem4ik.todo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "userLists")
public class UserLists implements Serializable {

    @Id
    @OneToOne(targetEntity = User.class)
    private final long userID;
    @Id
    @OneToOne(targetEntity = List.class)
    private final long listID;

}
