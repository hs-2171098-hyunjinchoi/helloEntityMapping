package kr.ac.hansung.cse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "instructor",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    // 연관 관계 편의 메소드
    public void addCourse(Course course) {
        courses.add(course);
        course.setInstructor(this);
    }
}
