/******************************************************************************
 * Copyright (c)  2/13/24, 9:32 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.student.app.models.Teacher;
import uz.asadbek.student.app.repository.TeacherRepo;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherService {
	private final TeacherRepo teacherRepo;
	@Autowired
	public TeacherService(TeacherRepo teacherRepo) {
		this.teacherRepo = teacherRepo;
	}


	public List<Teacher> getAllTeachers() {
		return teacherRepo.findAll();
	}
	public Teacher getOneTeacher(int id){
		return teacherRepo.findById(id).orElse(null);
	}

	@Transactional
	public void createTeacher(Teacher teacher)  {
		teacherRepo.save(teacher);
	}
	@Transactional
	public void updateTeacher(Teacher updatedTeacher) {
		teacherRepo.save(updatedTeacher);
	}
	@Transactional
	public void deleteTeacher(int id) {
		teacherRepo.deleteById(id);
	}
}
