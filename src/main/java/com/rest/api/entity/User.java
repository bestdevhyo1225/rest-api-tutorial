package com.rest.api.entity;

import lombok.*;

import javax.persistence.*;

@Builder // Builder를 사용할 수 있도록 한다.
@Entity // JPA의 Entity
@Getter
@Setter
@NoArgsConstructor // 인자가 없는 생성자를 자동으로 생생
@AllArgsConstructor // 인자가 모두 있는 생성자를 자동으로 생성
@Table(name = "user") // user 테이블과 매핑됨을 명시
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key 생성을 DB에 위임, PK 필드를 Auto Increment로 설정해 놓은 경우라고 보면된다.
    private Long msrl;

    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @Column(nullable = false, length = 100)
    private String name;
}
