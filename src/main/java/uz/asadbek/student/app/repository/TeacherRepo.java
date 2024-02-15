/******************************************************************************
 * Copyright (c)  2/13/24, 9:30 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.asadbek.student.app.models.Teacher;
@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
}
