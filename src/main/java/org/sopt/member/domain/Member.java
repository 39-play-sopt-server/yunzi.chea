package org.sopt.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.sopt.common.BaseEntity;
import org.sopt.member.exception.InvalidMemberException;
import jakarta.persistence.Id;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 자동 생성될 수 있도록 함.
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    protected Member() {}

    public Member(String name) {
        validateNotBlank(name, "이름");
        this.name = name;
    }

    private void validateNotBlank(String value, String fieldName) {
        if(value == null || value.isBlank()){
            throw new InvalidMemberException(fieldName + "은(는) 비어있을 수 없습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
