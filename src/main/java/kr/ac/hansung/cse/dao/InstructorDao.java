package kr.ac.hansung.cse.dao;

import kr.ac.hansung.cse.entity.Instructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class InstructorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    public Instructor findById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        return entityManager.createQuery("SELECT c FROM Instructor c", Instructor.class).getResultList();
    }

    public boolean isManaged (Instructor instructor) {
        return entityManager.contains(instructor);
    }

    public Instructor findByIdWithCourses(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        // 강제로 초기화하기 위해 컬렉션에 접근
        if (instructor != null) {
            instructor.getCourses().size(); // 컬렉션 초기화
        }

        return instructor;
    }
}